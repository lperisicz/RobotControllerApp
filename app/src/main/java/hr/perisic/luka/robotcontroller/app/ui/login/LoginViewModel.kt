package hr.perisic.luka.robotcontroller.app.ui.login

import hr.perisic.luka.domain.model.LoginRequestModel
import hr.perisic.luka.domain.usecase.GetIsUserLoggedUseCase
import hr.perisic.luka.domain.usecase.LoginUserUseCase
import hr.perisic.luka.robotcontroller.app.base.BaseViewModel
import kotlinx.coroutines.flow.map

internal class LoginViewModel(
    getIsUserLoggedUseCase: GetIsUserLoggedUseCase,
    private val loginUserUseCase: LoginUserUseCase
) : BaseViewModel<LoginViewState>() {

    init {
        query(getIsUserLoggedUseCase.invoke().map { LoginViewState.LoggedState(it) })
    }

    fun loginUser(email: String, password: String) {
        runCommand {
            loginUserUseCase(
                LoginRequestModel(
                    email,
                    password
                )
            )
        }
    }
}