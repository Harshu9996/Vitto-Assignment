package com.example.vittoassignment.di

import android.content.Context
import androidx.room.Room
import com.example.vittoassignment.connectivity.ConnectivityObserver
import com.example.vittoassignment.connectivity.ConnectivityObserverImpl
import com.example.vittoassignment.data.RepositoryImpl
import com.example.vittoassignment.data.local.RoomDB
import com.example.vittoassignment.data.local.RoomDao
import com.example.vittoassignment.data.remote.ApiService
import com.example.vittoassignment.domain.Repository
import com.example.vittoassignment.domain.usecase.AddFavouriteNews
import com.example.vittoassignment.domain.usecase.GetAllFavouriteNews
import com.example.vittoassignment.domain.usecase.GetNewsByCategory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val BASE_URL = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context): RoomDB{
        return Room.databaseBuilder(context = context, RoomDB::class.java,name = RoomDB.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideRoomDao(roomDb:RoomDB): RoomDao{
        return roomDb.getDao()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(@ApplicationContext context:Context): ConnectivityObserver{
        return ConnectivityObserverImpl(context)

    }

    @Provides
    @Singleton
    fun provideRepository(roomDao: RoomDao, apiService: ApiService, connectivityObserver: ConnectivityObserver): Repository{
        return RepositoryImpl(roomDao = roomDao, apiService = apiService, connectivityObserver = connectivityObserver)
    }

    @Provides
    @Singleton
    fun provideAddFavouriteNews(repository: Repository): AddFavouriteNews{
        return AddFavouriteNews(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllFavouriteNews(repository: Repository):GetAllFavouriteNews{
        return GetAllFavouriteNews(repository)
    }

    @Provides
    @Singleton
    fun provideGetNewsByCategory(repository: Repository):GetNewsByCategory{
        return GetNewsByCategory(repository)
    }

}