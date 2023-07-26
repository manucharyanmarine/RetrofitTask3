package com.example.task3.repository

import androidx.annotation.WorkerThread
import com.example.task3.data.Status
import com.example.task3.entity.Cat
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    val catList: Flow<List<Cat>>

    @WorkerThread
    suspend fun insert(cat: List<Cat>)
    suspend fun getCats(): Status<List<Cat>>
}