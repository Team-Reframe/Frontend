package com.example.reframe.api

data class StoreImportRequest(
    val query: String,
    val name: String,
    val x: Double,
    val y: Double,
    val radius: Int
)
