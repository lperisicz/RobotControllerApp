package hr.perisic.luka.domain.base.usecase

interface CommandUseCaseWithParam<Param> {

    suspend operator fun invoke(param: Param)
}