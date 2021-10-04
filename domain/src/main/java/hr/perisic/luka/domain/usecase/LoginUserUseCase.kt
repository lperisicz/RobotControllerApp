package hr.perisic.luka.domain.usecase

import hr.perisic.luka.domain.base.usecase.CommandUseCaseWithParam
import hr.perisic.luka.domain.model.LoginRequestModel
import hr.perisic.luka.domain.repository.AuthRepository

class LoginUserUseCase(
    private val authRepository: AuthRepository
) : CommandUseCaseWithParam<LoginRequestModel> {

    override suspend fun invoke(param: LoginRequestModel) {
        authRepository.loginUser(param)
    }
}