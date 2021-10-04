package hr.perisic.luka.data.api.converter.strategy

import android.graphics.Bitmap
import hr.perisic.luka.data.api.converter.exception.PGMConverterException
import hr.perisic.luka.data.api.converter.util.*
import java.io.BufferedReader

internal class PGMP2Strategy : PGMConverterStrategy {

    override suspend fun getBitmap(bufferedReader: BufferedReader): Bitmap {
        return bufferedReader.let {
            it.readNotNecessaryLine()
            val imageSize = it.readImageSize()
            it.readMaxGrayValue()
            it.getBitmap(imageSize)
        }
    }

    private fun BufferedReader.getBitmap(imageSize: Pair<Int, Int>): Bitmap {
        val intBuffer = (0 until imageSize.second).flatMap {
            readLine()?.getPixels(imageSize.first) ?: throw PGMConverterException.InvalidFormatException()
        }.toIntBuffer()
        return Bitmap.createBitmap(
            imageSize.first,
            imageSize.second,
            Bitmap.Config.ARGB_8888
        ).apply {
            copyPixelsFromBuffer(intBuffer)
        }
    }

    private fun String.getPixels(width: Int): List<Int> = trim()
        .split(INT_PIXEL_DELIMITER_1, INT_PIXEL_DELIMITER_2)
        .mapNotNull {
            it.toIntOrNull()?.toRGBInt()
        }.also { pixelList ->
            if (pixelList.size != width) throw PGMConverterException.InvalidFormatException("Wrong pixels size from line, expected: $width, found: ${pixelList.size}")
        }
}