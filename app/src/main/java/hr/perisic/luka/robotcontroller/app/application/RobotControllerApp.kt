package hr.perisic.luka.robotcontroller.app.application

import android.app.Application
import hr.perisic.luka.robotcontroller.app.di.appModule
import hr.perisic.luka.data.di.dataModules
import hr.perisic.luka.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RobotControllerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@RobotControllerApp)
            modules(
                listOf(
                    domainModule,
                    appModule
                ).plus(dataModules)
            )
        }
    }
}