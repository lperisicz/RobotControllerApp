package hr.perisic.luka.data.di

import hr.perisic.luka.data.repository.*
import hr.perisic.luka.domain.repository.*
import org.koin.dsl.module

internal val repositoryModule = module {

    single<MapRepository> {
        MapRepositoryImpl(
            mapApiSource = get()
        )
    }

    single<InfoRepository> {
        InfoRepositoryImpl(
            infoApiSource = get()
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            authApiSource = get(),
            authDbSource = get()
        )
    }

    single<NavigationPointsRepository> {
        NavigationPointsRepositoryImpl(
            navigationPointsApiSource = get()
        )
    }

    single<MapBarriersRepository> {
        MapBarriersRepositoryImpl(
            mapBarriersApiSource = get()
        )
    }
}