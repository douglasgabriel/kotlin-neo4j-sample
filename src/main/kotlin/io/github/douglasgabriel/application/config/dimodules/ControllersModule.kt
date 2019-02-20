package io.github.douglasgabriel.application.config.dimodules

import io.github.douglasgabriel.application.web.controllers.UsersController
import org.koin.dsl.module.module

object ControllersModule {

    fun modules() = module {
        single { UsersController(get()) }
    }
}
