package io.github.douglasgabriel.resources.persistence.user.dtos

import io.github.douglasgabriel.domain.user.entities.ChatGroup
import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity("ChatGroup")
data class ChatGroupNode(
        @Id @GeneratedValue val id: Long = 0,
        val name: String = "",
        @Relationship val members: List<UserNode> = emptyList()
) {
    fun toDomainModel(): ChatGroup = ChatGroup(
            this.id, this.name, this.members.map { it.toDomainModel() }
    )
}
