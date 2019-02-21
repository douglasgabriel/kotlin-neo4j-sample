package io.github.douglasgabriel.resources.persistence.user.dtos

import io.github.douglasgabriel.domain.user.entities.User
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity("User")
class UserNode(
        @Id var username: String? = null,
        var age: Int? = null,
        @Relationship(direction = Relationship.UNDIRECTED) var friends: List<UserNode> = emptyList()
) {
    fun toDomainModel(): User = User(
            this.username!!, this.age!!, this.friends.map { it.toDomainModel() }
    )
}

fun User.toDto(): UserNode = UserNode(
        this.username, this.age, this.friends.map { it.toDto() }
)
