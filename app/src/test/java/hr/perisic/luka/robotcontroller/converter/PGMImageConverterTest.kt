package hr.perisic.luka.robotcontroller.converter

/*import hr.perisic.luka.data.api.converter.PGMImageConverterImpl
import hr.perisic.luka.data.api.converter.exception.PGMConverterException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import java.io.ByteArrayInputStream
import java.io.InputStream

class PGMImageConverterTest {

    private val converter = PGMImageConverterImpl(
        p2Strategy = mock(),
        p5Strategy = mock(),
    )

    @Test
    fun testImageInvalid() = runBlocking {
        try {
            getInvalidInputStream().use { converter.convert(it) }
            Assert.fail()
        } catch (e: Exception) {
            assertTrue(e is PGMConverterException.InvalidFormatException)
        }
    }

    private fun getInvalidInputStream(): InputStream = ByteArrayInputStream(ByteArray(0))

    private fun runCoroutine() {
        GlobalScope.launch {
            delay(3000)
            methodTwo()
        }
        methodOne()
        Thread.sleep(5000)
    }
}*/