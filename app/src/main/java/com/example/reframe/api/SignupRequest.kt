package com.example.reframe.api

data class SignupRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val name: String
)
