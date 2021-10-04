package hr.perisic.luka.domain.base.usecase

import kotlinx.coroutines.flow.Flow

interface QueryUseCase<Result> {

    operator fun invoke(): Flow<Result>
}