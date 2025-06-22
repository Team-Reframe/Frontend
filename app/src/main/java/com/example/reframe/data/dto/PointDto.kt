package com.example.reframe.data.dto

data class ReceiptUploadResponse(
    val purchaseId: Long,
    val message: String // "영수증이 성공적으로 제출되었습니다."
)

// POST /reward 의 요청 Body
data class RewardRequest(
    val purchaseId: Long,
    val memberId: Long,
    val amount: Int
)

// POST /reward 의 응답
data class PointResponse(
    val points: Int,
    val purchaseId: Long,
    val memberId: Long,
    val pointType: String,
    val createdAt: String
)