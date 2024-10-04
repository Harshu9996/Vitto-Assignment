package com.example.vittoassignment.domain

import android.util.Log
import com.example.vittoassignment.data.model.NewsDto
import com.example.vittoassignment.util.Category

val TAG = "Mappers"

fun NewsDto.toNewsList(category: Category) : List<News>{
    Log.d(TAG, "toNewsList: NewsDTO = "+this)
    val articlesList  = this.articles
    val newsList = mutableListOf<News>()
    articlesList.forEach {it->
        newsList.add(
            News(
                title = it.title,
                description = it.description,
                imageUrl = it.urlToImage,
                author = it.author?: "Unknown",
                category = category.toString().lowercase()
            )
        )

    }

    return newsList
}