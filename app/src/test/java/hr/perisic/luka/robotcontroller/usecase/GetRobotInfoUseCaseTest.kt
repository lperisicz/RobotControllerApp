package hr.perisic.luka.robotcontroller.usecase

import hr.perisic.luka.domain.model.InfoModel
import hr.perisic.luka.domain.repository.InfoRepository
import hr.perisic.luka.domain.usecase.GetInfoUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

internal class GetRobotInfoUseCaseTest {

    private val infoRepository = mock<InfoRepository> {
        on { getInfo() } doReturn(
            flow {
                emit(
                    InfoModel(
                        speed = 12.1f,
                        battery = 89f,
                        timeOn = 23f
                    )
                )
            }
        )
    }
    private val getInfoUseCase = GetInfoUseCase(infoRepository)

    @Test
    fun testGetRobotInfo() = runBlocking {
        val first = getInfoUseCase().first()
        Assert.assertEquals(12.1f, first.speed)
    }

    @Test
fun runCoroutine() {
    CoroutineScope(Dispatchers.Default).launch {
        delay(3000)
        methodTwo()
    }
    methodOne()
    Thread.sleep(5000)
}

    private fun methodOne() {
        println("methodOne")
    }

    private fun methodTwo() {
        println("methodTwo")
    }
}
