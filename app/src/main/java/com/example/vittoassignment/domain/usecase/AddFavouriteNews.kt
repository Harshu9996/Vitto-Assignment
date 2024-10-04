package com.example.vittoassignment.domain.usecase

import com.example.vittoassignment.domain.News
import com.example.vittoassignment.domain.Repository
import javax.inject.Inject

class AddFavouriteNews @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(news:News){
        repository.addFavouriteNews(news)
    }
}