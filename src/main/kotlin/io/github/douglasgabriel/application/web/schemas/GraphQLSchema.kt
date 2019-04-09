package io.github.douglasgabriel.application.web.schemas

import com.github.pgutkowski.kgraphql.KGraphQL
import io.github.douglasgabriel.application.web.controllers.UsersController
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

object GraphQLSchema: KoinComponent {

    private val usersController by inject<UsersController>()

    val schema by lazy { registry() }

    private fun registry() = KGraphQL.schema {

        configure {
            useDefaultPrettyPrinter = true
        }

        query("user") {
            resolver { username: String -> usersController.getById(username) }
        }

        mutation("createUser") {
            resolver(usersController::createUser).withArgs {
                arg<String> { name = "username" }
                arg<Int> { name = "age" }
            }
        }
    }
}
