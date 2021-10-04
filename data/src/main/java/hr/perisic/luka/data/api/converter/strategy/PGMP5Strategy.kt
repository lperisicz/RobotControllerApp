package hr.perisic.luka.data.api.converter.strategy

import android.graphics.Bitmap
import hr.perisic.luka.data.api.converter.exception.PGMConverterException
import hr.perisic.luka.data.api.converter.util.*
import java.io.BufferedReader

internal class PGMP5Strategy: PGMConverterStrategy {

    override suspend fun getBitmap(bufferedReader: BufferedReader): Bitmap {
        return bufferedReader.let {
            val imageSize = it.readImageSize()
            it.readMaxGrayValue()
            it.getBitmap(imageSize)
        }
    }

    /*override suspend fun getBitmap(inputStream: InputStream): Bitmap {
        val it = inputStream.bufferedReader()
        val imageSize = 1274 to 863//it.readImageSize()
        //it.readMaxGrayValue()
        val readBytes = inputStream.readBytes().map { it.toUInt().toInt().toRGBInt() }.toIntArray()
        //val wrap = ByteBuffer.wrap(readBytes.plus(readBytes))
        val intBuffer = IntBuffer.wrap(readBytes.plus(readBytes).take(1099462).toIntArray())
        return Bitmap.createBitmap(
            imageSize.first,
            imageSize.second,
            Bitmap.Config.ARGB_8888
        ).apply {
            copyPixelsFromBuffer(intBuffer)
        }
    }*/

    private fun BufferedReader.getBitmap(imageSize: Pair<Int, Int>): Bitmap {
        val pixels = ArrayList<Int>()
        var line: String = ""
        while (readLine()?.also { line = it } != null) {
            pixels.addAll(line.getPixels())
        }
        val intBuffer = pixels.toIntBuffer() ?: throw PGMConverterException.InvalidFormatException()
        return Bitmap.createBitmap(
            imageSize.first,
            imageSize.second,
            Bitmap.Config.ARGB_8888
        ).apply {
            copyPixelsFromBuffer(intBuffer)
        }
    }

    private fun String.getPixels(): List<Int> = trim()
        .toByteArray(charset = Charsets.US_ASCII)
        .map {
            (it.toInt() + 256).toRGBInt()
        }
}