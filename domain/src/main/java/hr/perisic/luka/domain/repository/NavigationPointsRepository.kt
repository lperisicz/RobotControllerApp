package hr.perisic.luka.domain.repository

import hr.perisic.luka.domain.model.NavigationPointModel
import kotlinx.coroutines.flow.Flow

interface NavigationPointsRepository {

    fun getNavigationPoints(): Flow<List<NavigationPointModel>>
}