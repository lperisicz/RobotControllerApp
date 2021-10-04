package hr.perisic.luka.data.api.converter.util

import hr.perisic.luka.data.api.converter.exception.PGMConverterException
import java.io.BufferedReader
import java.nio.IntBuffer

const val IMAGE_SIZE_DELIMITER = " "
const val INT_PIXEL_DELIMITER_1 = " "
const val INT_PIXEL_DELIMITER_2 = "  "

fun Int.toRGBInt(): Int {
    return (this * 0x010101 + 0xFF000000).toInt()
}

fun BufferedReader.readNotNecessaryLine(): BufferedReader = this.also { readLine() }

fun BufferedReader.readImageSize(): Pair<Int, Int> = readLine()?.toImageSize() ?: throw PGMConverterException.InvalidFormatException()

fun BufferedReader.readMaxGrayValue() = readLine() ?: throw PGMConverterException.InvalidFormatException()

fun List<Int>.toIntBuffer(): IntBuffer = IntBuffer.wrap(this.toIntArray())

fun BufferedReader.readMagicNumber(): String = readLine()

private fun String.toImageSize(): Pair<Int, Int> {
    return try {
        this.split(IMAGE_SIZE_DELIMITER).let { it[0].toInt() to it[1].toInt() }
    } catch (e: Exception) {
        throw PGMConverterException.InvalidFormatException()
    }
}
