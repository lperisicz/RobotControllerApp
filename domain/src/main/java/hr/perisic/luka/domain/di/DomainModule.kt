package hr.perisic.luka.domain.di

import hr.perisic.luka.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {

    single {
        GetIsUserLoggedUseCase(
            authRepository = get()
        )
    }

    single {
        GetMapUseCase(
            mapRepository = get()
        )
    }

    single {
        GetInfoUseCase(
            infoRepository = get()
        )
    }

    single {
        GetRobotPositionUseCase()
    }

    single {
        GetNavigationPointsUseCase(
            navigationPointsRepository = get()
        )
    }

    single {
        GetMapBarriersUseCase(
            mapBarriersRepository = get()
        )
    }

    single {
        LoginUserUseCase(
            authRepository = get()
        )
    }
}