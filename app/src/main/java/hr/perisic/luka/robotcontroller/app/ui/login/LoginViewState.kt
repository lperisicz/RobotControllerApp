package hr.perisic.luka.robotcontroller.app.ui.login

internal sealed class LoginViewState {

    data class LoggedState(
        val isLoggedIn: Boolean
    ) : LoginViewState()

    data class LoginError(
        val message: String
    )
}