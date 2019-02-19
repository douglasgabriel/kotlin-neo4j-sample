package io.github.douglasgabriel.application.web

import io.github.douglasgabriel.application.config.EnvironmentConfig
import io.github.douglasgabriel.application.web.routes.Todo
import io.javalin.Javalin
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

object Api : KoinComponent {

    private val environmentConfig: EnvironmentConfig by inject()
    private val userRoutes: Todo by inject()

    fun start(): Javalin =
            Javalin.create().routes {
                userRoutes.register()
            }.start(environmentConfig.serverPort)
}
