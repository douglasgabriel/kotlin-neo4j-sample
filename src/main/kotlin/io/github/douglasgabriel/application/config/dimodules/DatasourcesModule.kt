package io.github.douglasgabriel.application.config.dimodules

import io.github.douglasgabriel.resources.datasources.Datasource
import io.github.douglasgabriel.resources.datasources.implementations.Neo4JDatasourceImpl
import org.koin.dsl.module.module
import org.neo4j.ogm.session.Session

object DatasourcesModule {

    fun modules() = module {
        single<Datasource<Session>> { Neo4JDatasourceImpl(get()) }
    }

}
