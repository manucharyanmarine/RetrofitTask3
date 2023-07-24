package com.example.task3.repository

import com.example.task3.Status
import com.example.task3.data.CatService
import com.example.task3.data.repository.CatRepository
import com.example.task3.entity.Cat


class CatRepositoryImpl(val catService: CatService) : CatRepository {
    override suspend fun getCats(): Status<List<Cat>> {
        val response = catService.getCats()
        return if (response.isSuccessful && response.body() != null) {
            Status.Success(response.body()?.data ?: listOf())
        } else {
            Status.Error(response.message(), response.code())
        }
    }
}