package com.example.reframe.api

data class ReviewResponse(
    val reviewId: Long,
    val content: String,
    val rating: Float,
    val createdAt: String
)
