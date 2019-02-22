package io.github.douglasgabriel.domain.user.repositories

import io.github.douglasgabriel.domain.user.entities.User

interface UsersRepository {

    fun createOrUpdate(user: User): User

    fun addFriend(userName: String, friendUsername: String): User

    fun retrieveById(userName: String): User
}
