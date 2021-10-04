package hr.perisic.luka.domain.usecase

import hr.perisic.luka.domain.base.usecase.QueryUseCase
import hr.perisic.luka.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class GetIsUserLoggedUseCase(
    private val authRepository: AuthRepository
) : QueryUseCase<Boolean> {

    override fun invoke(): Flow<Boolean> = authRepository.getIsLoggedIn()
}