package io.github.douglasgabriel.application.config.dimodules

import io.github.douglasgabriel.application.config.EnvironmentConfig
import org.koin.dsl.module.module

object ConfigModule {

    fun modules() = module {
        single { EnvironmentConfig() }
    }

}
