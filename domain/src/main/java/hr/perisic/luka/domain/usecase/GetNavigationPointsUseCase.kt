package hr.perisic.luka.domain.usecase

import hr.perisic.luka.domain.base.usecase.QueryUseCase
import hr.perisic.luka.domain.model.NavigationPointModel
import hr.perisic.luka.domain.repository.NavigationPointsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNavigationPointsUseCase(
    private val navigationPointsRepository: NavigationPointsRepository
): QueryUseCase<List<NavigationPointModel>> {

    override fun invoke(): Flow<List<NavigationPointModel>> = navigationPointsRepository.getNavigationPoints()
}