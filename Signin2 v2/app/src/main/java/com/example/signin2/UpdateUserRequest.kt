package com.example.signin2

data class UpdateUserRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String
)