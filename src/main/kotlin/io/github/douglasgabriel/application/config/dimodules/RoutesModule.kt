package io.github.douglasgabriel.application.config.dimodules

import io.github.douglasgabriel.application.web.routes.UsersRoutes
import org.koin.dsl.module.module

object RoutesModule {

    fun modules() = module {
        single { UsersRoutes(get(), get()) }
    }
}
