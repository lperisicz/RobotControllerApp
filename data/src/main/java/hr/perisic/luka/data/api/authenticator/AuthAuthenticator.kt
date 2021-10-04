package hr.perisic.luka.data.api.authenticator

import hr.perisic.luka.data.db.source.AuthDbSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

internal class AuthAuthenticator(
    private val authDbSource: AuthDbSource
): Authenticator {

    private val bgIOScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun authenticate(route: Route?, response: Response): Request? = response.let {
        if(response.code == 403) {
            deleteAuthModel()
        }
        null
    }

    private fun deleteAuthModel() {
        bgIOScope.launch {
            authDbSource.deleteAuthModel()
        }
    }
}