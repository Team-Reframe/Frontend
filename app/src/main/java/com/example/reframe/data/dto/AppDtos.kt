package com.example.reframe.data.dto

// === 리뷰 관련 DTO ===

// POST /reviews 요청 DTO
data class ReviewRequest(
    val memberId: Long,
    val purchaseId: Long,
    val storeId: Long,
    val content: String,
    val rating: Float
)

// GET /reviews/my 응답 DTO
data class MyReviewResponse(
    val reviewId: Long,
    val content: String,
    val rating: Float,
    val createdAt: String,
    val storeName: String,
    val purchaseId: Long,
    val storeId: Long
)

// GET /reviews/{reviewId} 응답
data class ReviewDetailResponse(
    val reviewId: Long,
    val content: String,
    val rating: Float,
    val createdAt: String
)

// GET /point/history 응답 리스트의 아이템
data class ReceiptHistoryResponse(
    val date: String,
    val storeLogoUrl: String,
    val storeName: String,
    val points: Int,
    val purchaseId: Long,
    val storeId: Long,
    var hasReview: Boolean
)

// GET /point/total 응답
data class TotalPointResponse(
    val total: Int
)