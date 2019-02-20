package io.github.douglasgabriel.resources.datasources.implementations

import io.github.douglasgabriel.application.config.EnvironmentConfig
import io.github.douglasgabriel.resources.datasources.Datasource
import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.session.Session
import org.neo4j.ogm.session.SessionFactory

private const val ENTITIES_BASE_PACKAGE = "io.github.douglasgabriel"

class Neo4JDatasourceImpl(
        environmentConfig: EnvironmentConfig
) : Datasource<Session> {

    private val configuration = Configuration.Builder()
            .uri(environmentConfig.neo4jUrl)
            .credentials(environmentConfig.neo4jUsername, environmentConfig.neo4jPassword)
            .build()

    private val sessionFactory = SessionFactory(configuration, ENTITIES_BASE_PACKAGE)

    private val datasource by lazy {
        sessionFactory.openSession()
    }

    override fun getDatabase(): Session = datasource
}
