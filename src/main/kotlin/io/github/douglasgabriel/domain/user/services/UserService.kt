package io.github.douglasgabriel.domain.user.services

import io.github.douglasgabriel.domain.user.entities.User

interface UserService {

    fun save(user: User): User

    fun addFriend(userName: String, friendUserName: String): User

    fun retrieveById(userName: String): User

    fun addToChatGroup(username: String, chatGroupName: String): User

    fun retrieveAllDirectContacts(username: String): List<User>
}
