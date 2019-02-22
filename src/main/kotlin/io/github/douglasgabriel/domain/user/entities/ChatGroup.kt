package io.github.douglasgabriel.domain.user.entities

data class ChatGroup(
        val name: String,
        val members: List<User>
)
