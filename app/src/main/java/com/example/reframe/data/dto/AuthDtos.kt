package com.example.reframe.data.dto

// POST /member/sign-up 요청
data class SignUpRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val name: String
)

// POST /member/sign-up 응답
data class SignUpResponse(
    val email: String,
    val name: String
)

// POST /auth/login 요청
data class LoginRequest(
    val email: String,
    val password: String
)

// POST /auth/login 응답
data class LoginResponse(
    val token: String,
    val name: String,
    val memberId: Long // API 명세에 따라 추가
)

data class UserInfoResponse(
    val name: String,
    val englishName: String?,
    val birthDate: String?,
    val phoneNumber: String?,
    val email: String
)