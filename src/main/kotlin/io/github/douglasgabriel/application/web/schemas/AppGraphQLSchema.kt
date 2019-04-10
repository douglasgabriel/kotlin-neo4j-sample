package io.github.douglasgabriel.application.web.schemas

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import io.github.douglasgabriel.application.web.controllers.UsersController
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring


object AppGraphQLSchema : KoinComponent {

    private val usersController by inject<UsersController>()

    val schema by lazy { registry() }

    private val schemaDefinition = System::class.java.getResource("/schema.graphqls").readText()

    private fun registry() =
            GraphQL.newGraphQL(buildSchema()).build()

    private fun buildSchema(): GraphQLSchema {
        val typeRegistry = SchemaParser().parse(schemaDefinition)
        val runtimeWiring = buildWiring()
        val schemaGenerator = SchemaGenerator()
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
    }

    private fun buildWiring(): RuntimeWiring =
            RuntimeWiring.newRuntimeWiring()
                    .type(
                            newTypeWiring("Query")
                                    .dataFetcher("userByUsername", usersController::getByUsername)
                                    .dataFetcher("userByAge", usersController::getByUsername)
                    )
                    .type(
                            newTypeWiring("user")
                                    .dataFetcher("userByUsername", usersController::getByUsername)
                    )
                    .build()
}
