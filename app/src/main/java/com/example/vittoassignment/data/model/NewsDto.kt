package com.example.vittoassignment.data.model

data class NewsDto(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)