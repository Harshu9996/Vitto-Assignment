package com.example.vittoassignment.presentation.home

import com.example.vittoassignment.domain.News
import com.example.vittoassignment.util.Category

sealed class HomeScreenEvents {
    data class AddFavouriteNews(val news: News): HomeScreenEvents()
    data class ChangTag(val tag:Category) : HomeScreenEvents()
}