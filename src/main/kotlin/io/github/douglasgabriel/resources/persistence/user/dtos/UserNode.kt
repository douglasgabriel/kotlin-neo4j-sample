package io.github.douglasgabriel.resources.persistence.user.dtos

import io.github.douglasgabriel.domain.user.entities.User
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity("User")
class UserNode(
    @Id var username: String? = null,
    var age: Int? = 0,
    @Relationship(direction = Relationship.UNDIRECTED) var friends: List<UserNode> = emptyList(),
    @Relationship("BELONGS_TO") var chatGroups: List<ChatGroupNode> = emptyList()
) {
    fun toDomainModel(depth: Int = 1, currentDepth: Int = 0): User = User(
        this.username!!, this.age!!, this.friends.mapNotNull {
            if (currentDepth < depth) it.toDomainModel(depth, currentDepth + 1) else null
        }, this.chatGroups.map { it.toDomainModel(depth, currentDepth) }
    )
}

fun User.toDto(): UserNode = UserNode(
    this.username, this.age, this.friends.map { it.toDto() }
)
