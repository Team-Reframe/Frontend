package com.example.reframe.data.dto

// POST /receipts/upload 응답
data class ReceiptUploadResponse(
    val purchaseId: Long,
    val storeName: String,
    val price: Int,
    val date: String
)

// POST /reward 요청
data class RewardRequest(
    val purchaseId: Long,
    val memberId: Long,
    val amount: Int
)

// POST /reward 응답
data class PointResponse(
    val points: Int,
    val purchaseId: Long,
    val memberId: Long,
    val pointType: String,
    val createdAt: String
)