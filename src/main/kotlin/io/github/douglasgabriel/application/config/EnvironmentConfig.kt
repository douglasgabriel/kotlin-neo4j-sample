package io.github.douglasgabriel.application.config

import com.natpryce.konfig.Configuration
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.getValue
import com.natpryce.konfig.intType

class EnvironmentConfig(
        configuration: Configuration = EnvironmentVariables()
) {
    val serverPort = configuration[SERVER_PORT]

    companion object {
        val SERVER_PORT by intType
    }

}
