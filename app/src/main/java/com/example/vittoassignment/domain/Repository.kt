package com.example.vittoassignment.domain

import com.example.vittoassignment.util.Category
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun addFavouriteNews(news: News)

    fun getAllNewsByCategory(category: Category): Flow<List<News>>

    fun getAllFavouriteNews(): Flow<List<News>>


}