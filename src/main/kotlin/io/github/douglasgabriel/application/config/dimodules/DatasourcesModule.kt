package io.github.douglasgabriel.application.config.dimodules

import io.github.douglasgabriel.resources.datasources.Datasource
import io.github.douglasgabriel.resources.datasources.implementations.Neo4JDatasourceImpl
import org.koin.dsl.module.module
import org.neo4j.driver.v1.Driver

object DatasourcesModule {

    fun modules() = module {
        single { Neo4JDatasourceImpl(get()) as Datasource<Driver> }
    }

}
