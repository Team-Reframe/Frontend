package com.example.reframe.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("member/sign-up")
    fun signUp(@Body request: SignupRequest): Call<SignupResponse>
}