package io.github.douglasgabriel.domain.user.repositories

import io.github.douglasgabriel.domain.user.entities.User

interface UsersRepository {

    fun createOrUpdate(user: User): User

    fun addFriend(username: String, friendUsername: String): User
}
