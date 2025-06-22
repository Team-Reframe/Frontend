package com.example.reframe.data.network

import com.example.reframe.data.dto.PointResponse
import com.example.reframe.data.dto.ReceiptUploadResponse
import com.example.reframe.data.dto.RewardRequest
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
}