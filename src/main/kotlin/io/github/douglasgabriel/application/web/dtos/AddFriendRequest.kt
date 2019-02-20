package io.github.douglasgabriel.application.web.dtos

import io.github.douglasgabriel.domain.user.entities.User


data class AddFriendRequest(
        val username: String
) {
    fun toDomain() = User(username)
}
