package com.example.task3.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat")
data class Cat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val breed: String,
    val coat: String,
    val country: String,
    val origin: String,
    val pattern: String
)