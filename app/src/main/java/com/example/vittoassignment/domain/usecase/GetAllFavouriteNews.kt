package com.example.vittoassignment.domain.usecase

import com.example.vittoassignment.domain.News
import com.example.vittoassignment.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavouriteNews @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Flow<List<News>> {
        return repository.getAllFavouriteNews()
    }
}