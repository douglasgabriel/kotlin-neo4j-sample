package io.github.douglasgabriel.resources.persistence.user.dtos

import io.github.douglasgabriel.domain.user.entities.ChatGroup
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity("ChatGroup")
data class ChatGroupNode(
    @Id var name: String? = null,
    @Relationship("BELONGS_TO", direction = Relationship.UNDIRECTED) var members: List<UserNode> = emptyList()
) {
    fun toDomainModel(depth: Int = 1, currentDepth: Int = 0): ChatGroup = ChatGroup(
        this.name!!, this.members.mapNotNull {
            if (currentDepth < depth) it.toDomainModel(depth, currentDepth + 1) else null
        }
    )
}
