@file:Suppress("RemoveExplicitTypeArguments")

package hr.perisic.luka.data.di

import hr.perisic.luka.data.api.authenticator.AuthAuthenticator
import hr.perisic.luka.data.api.interceptor.AuthInterceptor
import hr.perisic.luka.data.api.service.*
import hr.perisic.luka.data.api.source.*
import hr.perisic.luka.data.db.source.AuthDbSource
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TokenAuthenticator(): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        TODO("Not yet implemented")
    }
}

class TokenInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }
}

private const val BASE_URL = ""

internal val apiModule = module {

    single<Interceptor> {
        TokenInterceptor()
    }

    single<Authenticator> {
        TokenAuthenticator()
    }

    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single<AuthApi> {
        get<Retrofit>().create(AuthApi::class.java)
    }

    single<MapApi> {
        get<Retrofit>().create(MapApi::class.java)
    }

    single<PatrolPointsApi> {
        PatrolPointsApiMock() //get<Retrofit>().create(PatrolPointsApi::class.java)
    }

    single<MapBarriersApi> {
        MapBarriersApiMock() //get<Retrofit>().create(MapBarriersApi::class.java)
    }

    single<AuthApiSource> {
        AuthApiSourceImpl(
            authApi = get()
        )
    }

    single<MapApiSource> {
        MapApiSourceImpl(
            mapApi = get()
        )
    }

    single<InfoApiSource> {
        InfoApiSourceImpl(
            infoSocket = get()
        )
    }

    single<NavigationPointsApiSource> {
        NavigationPointsApiSourceImpl(
            navigationPointsApi = get()
        )
    }

    single<MapBarriersApiSource> {
        MapBarriersApiSourceImpl(
            mapBarriersApi = get()
        )
    }
}