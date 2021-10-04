package hr.perisic.luka.domain.base.usecase

import kotlinx.coroutines.flow.Flow

interface QueryUseCaseWithParam<Param, Result> {

    operator fun invoke(param: Param): Flow<Result>
}