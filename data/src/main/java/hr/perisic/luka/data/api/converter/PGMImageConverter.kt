package hr.perisic.luka.data.api.converter

import android.graphics.Bitmap
import java.io.InputStream

interface PGMImageConverter {

    suspend fun convert(inputStream: InputStream): Bitmap
}