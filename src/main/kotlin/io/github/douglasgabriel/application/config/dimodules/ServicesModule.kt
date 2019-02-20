package io.github.douglasgabriel.application.config.dimodules

import io.github.douglasgabriel.domain.user.services.UserService
import io.github.douglasgabriel.domain.user.services.implementations.UserServiceImpl
import org.koin.dsl.module.module

object ServicesModule {

    fun modules() = module {
        single<UserService> { UserServiceImpl(get()) }
    }
}
