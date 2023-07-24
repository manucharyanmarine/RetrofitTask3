package com.example.task3.data.repository

import androidx.annotation.WorkerThread
import com.example.task3.entity.Cat
import kotlinx.coroutines.flow.Flow

interface CatRoomRepository {
    val catList: Flow<List<Cat>>

    @WorkerThread
    suspend fun insert(cat: Cat)
}