package com.example.vittoassignment.data.remote

import com.example.vittoassignment.data.model.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("top-headlines")
    suspend fun getAllNewsByCategory(@Query("country") country: String = "us",
                                     @Query("category") category:String,
                                     @Query("apiKey") apiKey:String = "14b95a66f2004325bd10032974e8cd4f") : NewsDto
}