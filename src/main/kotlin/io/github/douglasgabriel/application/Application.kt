package io.github.douglasgabriel.application

import io.github.douglasgabriel.application.config.dimodules.DIModules
import io.github.douglasgabriel.application.web.Api
import org.koin.standalone.KoinComponent

class Application : KoinComponent {

    init {
        DIModules.start()
        Api.start()
    }
}
