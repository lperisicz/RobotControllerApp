package hr.perisic.luka.robotcontroller

import org.junit.Test

import org.junit.Assert.*
import kotlin.math.pow

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testTranslation() {
        fun translateValue(value: Float, k: Float, c: Float, d: Float): Float {
            return if(k > 1f)
                k.times(value.plus(d.times(1f.div(k.pow(2)).minus(c))))
            else
                value
        }

        val result = translateValue(2f, 2f, 0.5f, 5f)
        assertEquals(1.5f, result)

        val result2 = translateValue(2f, 1f, 0.5f, 5f)
        assertEquals(2f, result2)

        val result3 = translateValue(2f, 0.5f, 0.5f, 5f)
        assertEquals(1.5f, result3)
    }
}