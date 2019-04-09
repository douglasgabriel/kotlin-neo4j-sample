package io.github.douglasgabriel.application.web.controllers

import io.github.douglasgabriel.application.web.schemas.GraphQLSchema
import io.javalin.Context

class GraphQLController() {

    fun execute(ctx: Context) {
        val response = GraphQLSchema.schema.execute(ctx.body())

        ctx.result(response).contentType("application/json")
    }
}
