package com.example.vittoassignment.data

import android.util.Log
import com.example.vittoassignment.connectivity.ConnectivityObserver
import com.example.vittoassignment.data.local.RoomDao
import com.example.vittoassignment.data.remote.ApiService
import com.example.vittoassignment.domain.News
import com.example.vittoassignment.domain.Repository
import com.example.vittoassignment.domain.toNewsList
import com.example.vittoassignment.util.Category
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class RepositoryImpl(private val roomDao: RoomDao, private val apiService: ApiService,
    private val connectivityObserver: ConnectivityObserver): Repository {




    override suspend fun addFavouriteNews(news: News) {
        roomDao.addNews(news.copy(isFavourite = true))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllNewsByCategory(category: Category): Flow<List<News>> {


      return connectivityObserver.isNetworkAvailable.flatMapLatest {it->
           if(it){
                flow {
                    val data = apiService
                        .getAllNewsByCategory(category = category.toString().lowercase())
                        .toNewsList(category)
                    roomDao.addAllNews(data)
                   emit(data)
               }

           }else{
                roomDao.getAllNewsByCategory(category=category.toString().lowercase())
           }

        }

    }

    override fun getAllFavouriteNews(): Flow<List<News>> {
        return roomDao.getAllFavouriteNews()
    }
}