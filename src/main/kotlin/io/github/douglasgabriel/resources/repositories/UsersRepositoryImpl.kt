package io.github.douglasgabriel.resources.repositories

import io.github.douglasgabriel.domain.user.entities.User
import io.github.douglasgabriel.domain.user.repositories.UsersRepository
import io.github.douglasgabriel.resources.datasources.Datasource
import org.neo4j.ogm.session.Session

class UsersRepositoryImpl(
        private val datasource: Datasource<Session>
) : UsersRepository {

    override fun createOrUpdate(user: User): User = transaction {
        user.also { save(it) }
    }

    override fun addFriend(username: String, friendUsername: String): User = transaction {
        load(User::class.java, username)
                .addFriend(User(friendUsername))
                .also { save(it) }
    }

    private fun <T> transaction(op: Session.() -> T) =
            datasource.getDatabase().op()
}
