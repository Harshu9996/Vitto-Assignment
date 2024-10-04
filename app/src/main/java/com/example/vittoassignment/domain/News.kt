package com.example.vittoassignment.domain

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "news"
)
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title:String,
    val description: String,
    val category: String,
    val imageUrl: String?,
    val author: String?,
    val isFavourite:Boolean = false
)
