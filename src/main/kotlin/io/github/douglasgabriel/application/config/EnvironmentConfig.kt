package io.github.douglasgabriel.application.config

import com.natpryce.konfig.Configuration
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.getValue
import com.natpryce.konfig.intType
import com.natpryce.konfig.stringType

class EnvironmentConfig(
        configuration: Configuration = EnvironmentVariables()
) {
    val serverPort = configuration[SERVER_PORT]

    val neo4jUrl = configuration[NEO4J_URL]
    val neo4jUsername = configuration[NEO4J_USERNAME]
    val neo4jPassword = configuration[NEO4J_PASSWORD]

    companion object {
        val SERVER_PORT by intType

        val NEO4J_URL by stringType
        val NEO4J_USERNAME by stringType
        val NEO4J_PASSWORD by stringType
    }

}
