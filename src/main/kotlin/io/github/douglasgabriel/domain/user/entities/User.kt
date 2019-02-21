package io.github.douglasgabriel.domain.user.entities

data class User(
        val username: String,
        val age: Int,
        val friends: List<User>
) {

    fun addFriend(user: User) = this.copy(
            friends = friends.plus(user)
    )
}
