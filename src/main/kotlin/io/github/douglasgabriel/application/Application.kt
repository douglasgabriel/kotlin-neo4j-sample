package io.github.douglasgabriel.application

import io.github.douglasgabriel.application.config.dimodules.DIModules
import io.github.douglasgabriel.application.web.Api
import io.github.douglasgabriel.resources.datasources.Datasource
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import org.neo4j.driver.v1.Driver

class Application : KoinComponent {

    init {
        DIModules.start()
        Api.start()
    }
}
