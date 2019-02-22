package io.github.douglasgabriel.domain.user.entities

data class ChatGroup(
        val id: Long,
        val name: String,
        val members: List<User>
)
