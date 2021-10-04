package hr.perisic.luka.domain.usecase

import hr.perisic.luka.domain.base.usecase.QueryUseCase
import hr.perisic.luka.domain.model.InfoModel
import hr.perisic.luka.domain.repository.InfoRepository
import kotlinx.coroutines.flow.Flow

class GetInfoUseCase(
    private val infoRepository: InfoRepository
) : QueryUseCase<InfoModel> {

    override fun invoke(): Flow<InfoModel> = infoRepository.getInfo()
}