package io.github.douglasgabriel.application

import io.github.douglasgabriel.application.config.dimodules.DIModules
import io.github.douglasgabriel.application.web.Api
import io.javalin.Javalin
import org.koin.standalone.KoinComponent

class Application : KoinComponent {

    private val api: Javalin

    init {
        DIModules.start()
        api = Api.start()
    }

    fun stop() {
        api.stop()
    }

}
