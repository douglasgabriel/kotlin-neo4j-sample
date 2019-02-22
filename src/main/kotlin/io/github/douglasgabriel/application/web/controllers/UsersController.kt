package io.github.douglasgabriel.application.web.controllers

import io.github.douglasgabriel.application.web.dtos.AddChatGroupRequest
import io.github.douglasgabriel.application.web.dtos.AddFriendRequest
import io.github.douglasgabriel.application.web.dtos.CreateUserRequest
import io.github.douglasgabriel.domain.user.services.UserService
import io.javalin.Context

class UsersController(
    private val userService: UserService
) {

    fun retrieveById(ctx: Context) {
        val userName = ctx.pathParam("username")
        ctx.json(userService.retrieveById(userName))
    }

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
        val userName = ctx.pathParam("username")
        val friendUsername = ctx.body<AddFriendRequest>().username

        userService.addFriend(userName, friendUsername)
            .also {
                ctx.json(it)
            }
    }


    fun retrieveDirectContacts(ctx: Context) {
        val userName = ctx.pathParam("username")
        userService.retrieveAllDirectContacts(userName)
            .also {
                ctx.json(it)
            }
    }

    fun addChatGroup(ctx: Context) {
        val userName = ctx.pathParam("username")
        val chapGroupName = ctx.body<AddChatGroupRequest>().name

        userService.addToChatGroup(userName, chapGroupName)
            .also {
                ctx.json(it)
            }
    }

}
