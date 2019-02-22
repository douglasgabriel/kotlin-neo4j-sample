package io.github.douglasgabriel.application.web.routes

import io.github.douglasgabriel.application.web.controllers.UsersController
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.ApiBuilder.get
import org.koin.standalone.KoinComponent

class UsersRoutes(
    private val usersController: UsersController
) : KoinComponent {

    fun register() {
        path("users") {
            path(":username") {
                get(usersController::retrieveById)
            }

            post(usersController::create)

            path(":username/friends") {
                post(usersController::addFriend)
            }
        }
    }
}
