package hr.perisic.luka.data.api.converter

import android.graphics.Bitmap
import hr.perisic.luka.data.api.converter.exception.PGMConverterException
import hr.perisic.luka.data.api.converter.strategy.PGMConverterStrategy
import hr.perisic.luka.data.api.converter.util.FILE_FORMAT_P2
import hr.perisic.luka.data.api.converter.util.FILE_FORMAT_P5
import hr.perisic.luka.data.api.converter.util.readMagicNumber
import java.io.InputStream

internal class PGMImageConverterImpl(
    private val p2Strategy: PGMConverterStrategy,
    private val p5Strategy: PGMConverterStrategy
) : PGMImageConverter {

    /**
     * Read magic number from the first line of file and
     * use appropriate converter strategy.
     */
    override suspend fun convert(inputStream: InputStream): Bitmap {
        val bufferedReader = inputStream.bufferedReader()
        return try {
            when (bufferedReader.readMagicNumber()) {
                FILE_FORMAT_P2 -> p2Strategy.getBitmap(bufferedReader)
                FILE_FORMAT_P5 -> p5Strategy.getBitmap(bufferedReader)
                else -> throw PGMConverterException.UnsupportedFileFormatException
            }
        } catch (e: Exception) {
            throw PGMConverterException.InvalidFormatException(e.message ?: "")
        }
    }
}