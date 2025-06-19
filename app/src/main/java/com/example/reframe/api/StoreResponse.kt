package com.example.reframe.api

data class StoreResponse(
    val id: Long,
    val name: String,
    val category: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val phone: String,
    val openingHours: String,
    var imageResId: Int = 0
)
