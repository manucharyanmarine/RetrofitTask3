package com.example.task3.di

import com.example.task3.data.CatService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { createRetrofit().create(CatService::class.java) }
}

fun createRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder().addInterceptor(interceptor)
        .addInterceptor { chain ->
            val request = chain.request()
            request.newBuilder().addHeader("Content-Type", "application/json").build()
            chain.proceed(request)
        }.build()

    return Retrofit.Builder()
        .client(client)
        .baseUrl("https://catfact.ninja/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
