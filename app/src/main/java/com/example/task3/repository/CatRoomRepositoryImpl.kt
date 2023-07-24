package com.example.task3.repository

import androidx.annotation.WorkerThread
import com.example.task3.data.CatDao
import com.example.task3.data.repository.CatRoomRepository
import com.example.task3.entity.Cat
import kotlinx.coroutines.flow.Flow

class CatRoomRepositoryImpl(private val catDao: CatDao) : CatRoomRepository {
    override val catList: Flow<List<Cat>> = catDao.getCatList()

    @WorkerThread
    override suspend fun insert(cat: Cat) {
        catDao.addCat(cat)
    }
}