package io.github.douglasgabriel.application.web.dtos

import io.github.douglasgabriel.domain.user.entities.User

data class CreateUserRequest(
        val username: String,
        val age: Int
) {
    fun toDomain() = User(
            username, age
    )
}
