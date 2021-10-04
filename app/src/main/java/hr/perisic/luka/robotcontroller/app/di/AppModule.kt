package hr.perisic.luka.robotcontroller.app.di

import hr.perisic.luka.robotcontroller.app.ui.barrier.MapBarriersViewModel
import hr.perisic.luka.robotcontroller.app.ui.info.InfoViewModel
import hr.perisic.luka.robotcontroller.app.ui.login.LoginViewModel
import hr.perisic.luka.robotcontroller.app.ui.main.MapViewModel
import hr.perisic.luka.robotcontroller.app.ui.points.NavigationPointsViewModel
import hr.perisic.luka.robotcontroller.app.ui.position.RobotPositionViewModel
import hr.perisic.luka.robotcontroller.app.ui.scale.MapScalePublisher
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    viewModel {
        LoginViewModel(
            getIsUserLoggedUseCase = get(),
            loginUserUseCase = get()
        )
    }

    viewModel {
        InfoViewModel(
            getInfoUseCase = get()
        )
    }

    viewModel {
        MapViewModel(
            getMapUseCase = get(),
            mapScalePublisher = get()
        )
    }

    viewModel {
        RobotPositionViewModel(
            getRobotPositionUseCase = get(),
            mapScalePublisher = get()
        )
    }

    viewModel {
        NavigationPointsViewModel(
            getNavigationPointsUseCase = get()
        )
    }

    viewModel {
        MapBarriersViewModel(
            getMapBarriersUseCase = get()
        )
    }

    single {
        MapScalePublisher(
            getMapUseCase = get()
        )
    }
}