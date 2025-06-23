package com.example.reframe.data.network

import com.example.reframe.data.dto.*
import com.example.reframe.data.PointHistoryItem
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("/receipts/upload")
    suspend fun uploadReceipt(
        @Query("memberId") memberId: Long,
        @Part file: MultipartBody.Part
    ): Response<ReceiptUploadResponse>

    @POST("/reward")
    suspend fun requestReward(
        @Body rewardRequest: RewardRequest
    ): Response<PointResponse>

    @POST("/member/sign-up")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // 포인트 내역 관련
    @GET("/point/total")
    suspend fun getTotalPoints(@Query("memberId") memberId: Long): Response<TotalPointResponse>

    @GET("/point/history")
    suspend fun getReceiptHistory(@Query("memberId") memberId: Long): Response<List<ReceiptHistoryResponse>>

    // 리뷰 관련
    @POST("/reviews")
    suspend fun postReview(@Body reviewRequest: ReviewRequest): Response<Unit>

    @GET("/reviews/my")
    suspend fun getMyReviews(@Query("memberId") memberId: Long): Response<List<MyReviewResponse>>

    @DELETE("/reviews/{reviewId}")
    suspend fun deleteReview(
        @Path("reviewId") reviewId: Long,
        @Query("memberId") memberId: Long
    ): Response<Unit>

    @GET("/reviews/{reviewId}")
    suspend fun getReviewDetail(@Path("reviewId") reviewId: Long): Response<ReviewDetailResponse>

    // 내 정보 조회 API (API 명세에 없으므로 엔드포인트 가정)
    @GET("/member/my-info")
    suspend fun getMyInfo(@Query("memberId") memberId: Long): Response<UserInfoResponse>

    // 포인트 내역 조회 API
    @GET("/point/history")
    suspend fun getPointHistory(@Query("memberId") memberId: Long): Response<List<PointHistoryItem>>
    // 로그아웃 API
    @POST("/auth/logout")
    suspend fun logout(@Header("Authorization") token: String): Response<Unit>
}