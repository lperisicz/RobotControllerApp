package hr.perisic.luka.data.api.converter.exception

sealed class PGMConverterException(
    message: String
) : Exception(message) {

    object UnsupportedFileFormatException : PGMConverterException("")

    class InvalidFormatException(message: String = "") : PGMConverterException(message)
}