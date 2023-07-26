package com.example.task3.repository

import androidx.annotation.WorkerThread
import com.example.task3.data.CatDao
import com.example.task3.data.Status
import com.example.task3.data.CatService
import com.example.task3.entity.Cat
import kotlinx.coroutines.flow.Flow

class CatRepositoryImpl(private val catService: CatService, private val catDao: CatDao) :
    CatRepository {
    override val catList: Flow<List<Cat>> = catDao.getCatList()

    @WorkerThread
    override suspend fun insert(cat: List<Cat>) {
        catDao.addCat(cat)
    }

    override suspend fun getCats(): Status<List<Cat>> {
        val response = catService.getCats()
        return if (response.isSuccessful && response.body() != null) {
            val catList = arrayListOf<Cat>()
            val dataList = Status.Success(response.body()?.data ?: listOf())
            dataList.data.forEach {
                catList.add(Cat(it.id, it.breed, it.origin, it.pattern))
            }
            Status.Success(catList)
        } else {
            Status.Error(response.message(), response.code())
        }
    }
}