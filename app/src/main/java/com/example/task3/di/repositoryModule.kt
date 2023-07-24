package com.example.task3.di

import com.example.task3.repository.CatRepositoryImpl
import com.example.task3.repository.CatRoomRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory { CatRoomRepositoryImpl(get()) }
    factory { CatRepositoryImpl(get()) }
}