package hr.perisic.luka.domain.usecase

import hr.perisic.luka.domain.base.usecase.QueryUseCase
import hr.perisic.luka.domain.model.MapModel
import hr.perisic.luka.domain.repository.MapRepository
import kotlinx.coroutines.flow.Flow

class GetMapUseCase(
    private val mapRepository: MapRepository
): QueryUseCase<MapModel> {

    override fun invoke(): Flow<MapModel> = mapRepository.getMap()
}