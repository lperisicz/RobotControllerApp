package hr.perisic.luka.data.repository

import hr.perisic.luka.data.api.source.InfoApiSource
import hr.perisic.luka.domain.model.InfoModel
import hr.perisic.luka.domain.repository.InfoRepository
import kotlinx.coroutines.flow.Flow

internal class InfoRepositoryImpl(
    private val infoApiSource: InfoApiSource
): InfoRepository {

    override fun getInfo(): Flow<InfoModel> = infoApiSource.getInfo()
}