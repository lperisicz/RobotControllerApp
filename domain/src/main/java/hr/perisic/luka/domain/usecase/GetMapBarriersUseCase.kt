package hr.perisic.luka.domain.usecase

import hr.perisic.luka.domain.base.usecase.QueryUseCase
import hr.perisic.luka.domain.model.MapBarrierModel
import hr.perisic.luka.domain.repository.MapBarriersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMapBarriersUseCase(
    private val mapBarriersRepository: MapBarriersRepository
): QueryUseCase<List<MapBarrierModel>> {

    override fun invoke(): Flow<List<MapBarrierModel>> = mapBarriersRepository.getMapBarriers()
}