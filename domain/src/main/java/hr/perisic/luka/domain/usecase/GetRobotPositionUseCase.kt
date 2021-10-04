package hr.perisic.luka.domain.usecase

import hr.perisic.luka.domain.base.usecase.QueryUseCase
import hr.perisic.luka.domain.model.RobotPositionModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class GetRobotPositionUseCase: QueryUseCase<RobotPositionModel> {

    private companion object {
        const val REFRESH_RATE = 400L
    }

    override fun invoke(): Flow<RobotPositionModel> = flow {
        var currentPosition = RobotPositionModel(
            x = 500, //Random.nextInt(1440),
            y = 1400 //Random.nextInt(3200)
        )
        var newPosition: RobotPositionModel?
        while (true) {
            newPosition = RobotPositionModel(
                currentPosition.x + Random.nextInt(-10, 10),
                currentPosition.y + Random.nextInt(-10, 10),
            )
            currentPosition = newPosition
            emit(newPosition)
            delay(REFRESH_RATE)
        }
    }
}