package com.example.vittoassignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.vittoassignment.domain.News
import kotlinx.coroutines.flow.Flow


@Dao
interface RoomDao {

    @Insert
    suspend fun addNews(news:News)

    @Insert
    suspend fun addAllNews(newsList:List<News>)

    @Query("SELECT * FROM news WHERE category = :category")
    fun getAllNewsByCategory(category:String): Flow<List<News>>

    @Query("SELECT * FROM news WHERE isFavourite = 1")
    fun getAllFavouriteNews(): Flow<List<News>>
}