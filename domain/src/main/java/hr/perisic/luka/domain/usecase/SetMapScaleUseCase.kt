package hr.perisic.luka.domain.usecase

import hr.perisic.luka.domain.base.usecase.CommandUseCaseWithParam

class SetMapScaleUseCase: CommandUseCaseWithParam<Int> {

    override suspend fun invoke(param: Int) {
        TODO("Not yet implemented")
    }
}