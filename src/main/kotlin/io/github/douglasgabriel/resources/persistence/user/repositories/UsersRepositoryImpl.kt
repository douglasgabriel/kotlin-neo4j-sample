package io.github.douglasgabriel.resources.persistence.user.repositories

import io.github.douglasgabriel.domain.user.entities.User
import io.github.douglasgabriel.domain.user.repositories.UsersRepository
import io.github.douglasgabriel.resources.datasources.Datasource
import io.github.douglasgabriel.resources.persistence.user.dtos.UserNode
import io.github.douglasgabriel.resources.persistence.user.dtos.toDto
import org.neo4j.ogm.session.Session

class UsersRepositoryImpl(
        private val datasource: Datasource<Session>
) : UsersRepository {

    override fun createOrUpdate(user: User): User = transaction {
        user.also { save(it.toDto()) }
    }

    override fun addFriend(username: String, friendUsername: String): User = transaction {
        val depth = 2

        load(UserNode::class.java, username, depth)
                .apply { this.friends = this.friends.plus(UserNode(friendUsername)) }
                .also { save(it) }
                .toDomainModel()
    }

    private fun <T> transaction(op: Session.() -> T) =
            datasource.getDatabase().op()
}
