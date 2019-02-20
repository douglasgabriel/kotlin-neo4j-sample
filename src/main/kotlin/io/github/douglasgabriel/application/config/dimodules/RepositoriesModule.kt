package io.github.douglasgabriel.application.config.dimodules

import io.github.douglasgabriel.domain.user.repositories.UsersRepository
import io.github.douglasgabriel.resources.repositories.UsersRepositoryImpl
import org.koin.dsl.module.module

object RepositoriesModule {

    fun modules() = module {
        single<UsersRepository> { UsersRepositoryImpl(get()) }
    }
}
