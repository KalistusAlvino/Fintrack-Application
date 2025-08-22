package com.example.fintrack.di.model.user

data class User(
    val id: Int,
    val username: String,
    val token: String,
    val isLogin: Boolean = false
)
