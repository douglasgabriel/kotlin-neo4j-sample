package io.github.douglasgabriel.resources.persistence.user.repositories

import io.github.douglasgabriel.domain.user.entities.User
import io.github.douglasgabriel.domain.user.repositories.UsersRepository
import io.github.douglasgabriel.resources.datasources.Datasource
import io.github.douglasgabriel.resources.persistence.user.dtos.ChatGroupNode
import io.github.douglasgabriel.resources.persistence.user.dtos.UserNode
import io.github.douglasgabriel.resources.persistence.user.dtos.toDto
import org.neo4j.ogm.session.Session

class UsersRepositoryImpl(
        private val datasource: Datasource<Session>
) : UsersRepository {

    override fun createOrUpdate(user: User): User = transaction {
        user.also { save(it.toDto()) }
    }

    override fun addFriend(userName: String, friendUsername: String): User = transaction {
        val depth = 1

        load(UserNode::class.java, userName, depth)
                .apply { this.friends = this.friends.plus(UserNode(friendUsername)) }
                .also { save(it) }
                .toDomainModel(depth)
    }

    override fun addToGroup(username: String, chatGroupName: String) = transaction {
        val queryString = """
            MATCH (u:User {username: {username}}), (c:ChatGroup {name: {chatGroupName}})
            CREATE (u)-[:BELONGS_TO]->(c)
            RETURN u
        """.trimIndent()

        val params = mapOf(
                "username" to username,
                "chatGroupName" to chatGroupName
        )

        query(UserNode::class.java, queryString, params).first().toDomainModel()

//        load(UserNode::class.java, username)
//            .apply { this.chatGroups = this.chatGroups.plus(ChatGroupNode(chatGroupName)) }
//            .also { save(it) }
//            .toDomainModel()
    }

    override fun retrieveById(userName: String) = transaction {
        val depth = 4
        load(UserNode::class.java, userName).toDomainModel(depth)
    }

    override fun retrieveAllDirectContacts(username: String) = transaction {
        val queryString =
                "MATCH(u:User{username: {username}})-[:BELONGS_TO]->(c:ChatGroup)<-[:BELONGS_TO]-(u2:User) RETURN u2.username"

        query(queryString, mapOf("username" to username))
                .let {
                    it.map {
                        User(it["u2.username"].toString(), 0, emptyList(), emptyList())
                    }
                }

//        query(UserNode::class.java, queryString, mapOf("username" to username)).map {
//            it.toDomainModel()
//        }
    }

    private fun <T> transaction(op: Session.() -> T) =
            datasource.getDatabase().op()
}
