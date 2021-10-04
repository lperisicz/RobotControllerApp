package hr.perisic.luka.data.di

import hr.perisic.luka.data.api.socket.InfoSocket
import hr.perisic.luka.data.api.socket.InfoSocketMock
import org.koin.dsl.module

internal val socketModule = module {

    single<InfoSocket> {
        InfoSocketMock()
    }
}