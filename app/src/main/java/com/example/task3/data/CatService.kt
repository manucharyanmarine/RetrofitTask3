package com.example.task3.data

import com.example.task3.entity.Cat
import com.example.task3.entity.CatDetails
import retrofit2.Response
import retrofit2.http.GET

interface CatService {
    @GET("breeds")
    suspend fun getCats(): Response<CatDetails>
}