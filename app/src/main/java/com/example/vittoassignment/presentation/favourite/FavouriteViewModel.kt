package com.example.vittoassignment.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vittoassignment.domain.News
import com.example.vittoassignment.domain.usecase.GetAllFavouriteNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val getAllFavouriteNews: GetAllFavouriteNews):ViewModel() {

    private val _favouriteNews = MutableStateFlow<List<News>?>(null)
    val favouriteNews = _favouriteNews.asStateFlow()

    init {
        viewModelScope.launch {
            getAllFavouriteNews.invoke().collect{it->
                _favouriteNews.value = it

            }
        }

    }
}