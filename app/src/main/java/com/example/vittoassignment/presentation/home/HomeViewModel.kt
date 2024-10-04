package com.example.vittoassignment.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vittoassignment.domain.News
import com.example.vittoassignment.domain.usecase.AddFavouriteNews
import com.example.vittoassignment.domain.usecase.GetNewsByCategory
import com.example.vittoassignment.util.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getNewsByCategory:GetNewsByCategory,
    private val addFavouriteNews: AddFavouriteNews): ViewModel() {


    private var _newsList = MutableStateFlow<List<News>?>(null)
    val newsList = _newsList.asStateFlow()

    val DEFAULT_CATEGORY = Category.Technology


    init {

        viewModelScope.launch {

           fetchNews()
        }

    }

    fun onEvent(event:HomeScreenEvents){
        when(event){
            is HomeScreenEvents.AddFavouriteNews->{
                viewModelScope.launch {
                    addFavouriteNews.invoke(event.news)
                }
            }

            is HomeScreenEvents.ChangTag -> {
                viewModelScope.launch {
                    fetchNews(event.tag)
                }

            }
        }

    }

    suspend fun fetchNews(category: Category = DEFAULT_CATEGORY){
        getNewsByCategory.invoke(category).collect { it->
            _newsList.value = it

        }
    }



}