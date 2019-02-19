package io.github.douglasgabriel.resources.datasources.implementations

import io.github.douglasgabriel.application.config.EnvironmentConfig
import io.github.douglasgabriel.resources.datasources.Datasource
import org.neo4j.driver.v1.AuthTokens
import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.GraphDatabase

class Neo4JDatasourceImpl(
        private val environmentConfig: EnvironmentConfig
) : Datasource<Driver> {

    private val datasource by lazy {
        GraphDatabase.driver(
                environmentConfig.neo4jUrl,
                AuthTokens.basic(
                        environmentConfig.neo4jUsername, environmentConfig.neo4jPassword
                )
        )
    }

    override fun getDatabase(): Driver = datasource
}
