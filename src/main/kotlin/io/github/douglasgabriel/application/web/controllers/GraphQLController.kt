package io.github.douglasgabriel.application.web.controllers

import io.github.douglasgabriel.application.web.schemas.AppGraphQLSchema
import io.javalin.Context

class GraphQLController() {

    fun execute(ctx: Context) {
        val response = AppGraphQLSchema.schema.execute(ctx.body())

        ctx.json(response.getData<Any>())
    }
}
