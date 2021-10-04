package hr.perisic.luka.data.api.socket

import hr.perisic.luka.domain.model.InfoModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class InfoSocketMock: InfoSocket {

    private companion object {
        const val REFRESH_RATE = 3000L
    }

    override fun getInfo(): Flow<InfoModel> = flow {
        var counter = 0
        emit(getValue(counter))
        while (true) {
            delay(REFRESH_RATE)
            counter = changeCounter(counter)
            emit(getValue(counter))
        }
    }

    private fun changeCounter(counter: Int): Int {
        return if(counter > 0) {
            0
        } else {
            counter + 1
        }
    }

    private fun getValue(counter: Int): InfoModel {
        return if(counter == 0) {
            InfoModel(
                speed = 12.34f,
                battery = 37.56f,
                timeOn = 23f
            )
        } else {
            InfoModel(
                speed = 14.94f,
                battery = 36.2f,
                timeOn = 24f
            )
        }
    }
}