package componentTest.components

import io.github.douglasgabriel.application.config.dimodules.DatasourcesModule
import io.mockk.every
import io.mockk.mockkObject
import org.koin.dsl.module.module

object DIMock {

    private val databaseMocks = module {
        single { Neo4JDatasource.getDatabase() }
    }

    fun mockModules() {
        mockkObject(DatasourcesModule)

        every { DatasourcesModule.modules() } returns databaseMocks
    }
}
