package hr.perisic.luka.domain.repository

import hr.perisic.luka.domain.model.InfoModel
import kotlinx.coroutines.flow.Flow

interface InfoRepository {

    fun getInfo(): Flow<InfoModel>
}