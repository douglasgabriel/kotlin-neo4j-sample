package io.github.douglasgabriel.domain.user.services.implementations

import io.github.douglasgabriel.domain.user.entities.User
import io.github.douglasgabriel.domain.user.repositories.UsersRepository
import io.github.douglasgabriel.domain.user.services.UserService

class UserServiceImpl(
    private val usersRepository: UsersRepository
) : UserService {
    override fun retrieveById(userName: String) = usersRepository.retrieveById(userName)

    override fun save(user: User): User = usersRepository.createOrUpdate(user)

    override fun addFriend(userName: String, friendUserName: String): User =
            usersRepository.addFriend(userName, friendUserName)
}
