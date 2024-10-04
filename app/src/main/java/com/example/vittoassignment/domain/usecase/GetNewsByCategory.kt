package com.example.vittoassignment.domain.usecase

import android.util.Log
import com.example.vittoassignment.domain.News
import com.example.vittoassignment.domain.Repository
import com.example.vittoassignment.util.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsByCategory @Inject constructor(val repository: Repository) {
    operator fun invoke(category: Category): Flow<List<News>> {
        return repository.getAllNewsByCategory(category)

    }
}