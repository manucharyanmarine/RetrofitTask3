package com.example.task3.di

import com.example.task3.repository.CatRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory { CatRepositoryImpl(get(), get()) }
}