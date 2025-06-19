package com.example.reframe.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("member/sign-up")
    fun signUp(@Body request: SignupRequest): Call<SignupResponse>

    @GET("members/me")
    fun getMyInfo(@Header("Authorization") token: String): Call<MyInfoResponse>

    @POST("stores/import")
    fun importStore(@Body request: StoreImportRequest): Call<StoreResponse>

    @GET("stores")
    fun getAllStores(): Call<List<StoreResponse>>

    @GET("stores/{storeId}")
    fun getStoreDetail(@Path("storeId") id: Long): Call<StoreResponse>

    @GET("stores/map")
    fun getStoresInMap(
        @Query("swLat") swLat: Double,
        @Query("swLng") swLng: Double,
        @Query("neLat") neLat: Double,
        @Query("neLng") neLng: Double
    ): Call<List<StoreResponse>>
}