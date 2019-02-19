package io.github.douglasgabriel.application

import io.github.douglasgabriel.application.config.dimodules.DIModules
import io.github.douglasgabriel.application.web.Api

class Application {

    init {
        DIModules.start()
        Api.start()
    }
}
