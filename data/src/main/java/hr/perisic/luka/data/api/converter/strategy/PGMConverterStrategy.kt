package hr.perisic.luka.data.api.converter.strategy

import android.graphics.Bitmap
import java.io.BufferedReader

interface PGMConverterStrategy {

    suspend fun getBitmap(bufferedReader: BufferedReader): Bitmap
}