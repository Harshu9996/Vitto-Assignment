package com.example.vittoassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vittoassignment.domain.News


@Database(
    entities = [News::class],
    version = 1
)
abstract class RoomDB : RoomDatabase() {
    abstract fun getDao() : RoomDao

    companion object{
        const val DATABASE_NAME = "Vitto Database"
    }
}