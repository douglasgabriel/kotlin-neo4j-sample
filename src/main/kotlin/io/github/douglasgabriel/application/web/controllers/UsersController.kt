package io.github.douglasgabriel.application.web.controllers

import io.github.douglasgabriel.application.web.dtos.AddFriendRequest
import io.github.douglasgabriel.application.web.dtos.CreateUserRequest
import io.github.douglasgabriel.domain.user.entities.User
import io.github.douglasgabriel.domain.user.services.UserService
import io.javalin.Context

class UsersController(
        private val userService: UserService
) {

    fun create(ctx: Context) {
        ctx.body<CreateUserRequest>()
                .toDomain()
                .let {
                    userService.save(it)
                }
                .also {
                    ctx.json(it)
                }
    }

    fun addFriend(ctx: Context) {
        val username = ctx.pathParam("username")
        val friendUsername = ctx.body<AddFriendRequest>().username

        userService.addFriend(username, friendUsername)
                .also {
                    ctx.json(it)
                }
    }

}
