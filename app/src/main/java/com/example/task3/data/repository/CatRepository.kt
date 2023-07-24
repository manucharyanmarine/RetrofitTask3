package com.example.task3.data.repository

import com.example.task3.Status
import com.example.task3.entity.Cat

interface CatRepository {
    suspend fun getCats(): Status<List<Cat>>
}