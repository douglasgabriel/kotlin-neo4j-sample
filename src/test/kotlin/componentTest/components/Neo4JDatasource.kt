package componentTest.components

import io.github.douglasgabriel.resources.datasources.Datasource
import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.session.Session
import org.neo4j.ogm.session.SessionFactory

object Neo4JDatasource {

    fun getDatabase() = object : Datasource<Session> {
        private val config = Configuration.Builder().build()
        private val factory = SessionFactory(config, "io.github.douglasgabriel")

        override fun getDatabase(): Session = factory.openSession()
    }
}
