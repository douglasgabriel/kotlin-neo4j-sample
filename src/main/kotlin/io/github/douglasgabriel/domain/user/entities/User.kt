package io.github.douglasgabriel.domain.user.entities

import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
data class User(
        @Id var username: String? = null,
        var age: Int? = null,
        @Relationship("FRIENDS") var friends: List<User> = emptyList()
) {

    fun addFriend(user: User) = this.copy(
            friends = friends.plus(user)
    )
}
