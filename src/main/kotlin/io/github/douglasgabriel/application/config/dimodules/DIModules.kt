package io.github.douglasgabriel.application.config.dimodules

import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin

object DIModules {

    fun start() = startKoin(
            listOf(
                    ControllersModule.modules(),
                    DatasourcesModule.modules(),
                    ServicesModule.modules(),
                    ConfigModule.modules(),
                    RepositoriesModule.modules(),
                    RoutesModule.modules()
            )
    )

    fun stop() = stopKoin()
}
