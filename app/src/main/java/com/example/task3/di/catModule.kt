package com.example.task3.di

import com.example.task3.data.CatDatabase
import org.koin.dsl.module

val catModule = module {
    single { CatDatabase.getDatabase(get()).catDao() }
}