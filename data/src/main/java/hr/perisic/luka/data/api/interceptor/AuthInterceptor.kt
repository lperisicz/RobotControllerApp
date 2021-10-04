package hr.perisic.luka.data.api.interceptor

import hr.perisic.luka.data.db.source.AuthDbSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

private const val AUTHORIZATION_HEADER = "Cookie"
private const val BEARER_PLACEHOLDER = "jwt=%s"

internal class AuthInterceptor(
    private val authDbSource: AuthDbSource
) : Interceptor {

    private val bgIOScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var token: String = ""

    init {
        bgIOScope.launch {
            authDbSource
                .getAuthModel()
                .onEach { println("DB TOKEN FETCHED: $it") }
                .collect { this@AuthInterceptor.token = it?.token ?: "" }
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response = chain
        .request()
        .newBuilder()
        .apply {
            addHeader(AUTHORIZATION_HEADER, String.format(BEARER_PLACEHOLDER, token))
        }
        .build()
        .let { chain.proceed(it).also { response ->
            if(response.code == 403) {
                bgIOScope.launch {
                    authDbSource.deleteAuthModel()
                }
            }
        } }
}