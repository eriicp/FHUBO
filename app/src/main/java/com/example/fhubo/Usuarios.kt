package com.example.fhubo

object Usuarios {
    private val usersList: MutableList<User> = mutableListOf()

    fun addUser(user: User) {
        usersList.add(user)
    }

    fun getUser(email: String): User? {
        return usersList.find { it.email == email }
    }
}