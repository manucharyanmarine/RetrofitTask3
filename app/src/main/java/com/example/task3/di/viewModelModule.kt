package com.example.task3.di

import com.example.task3.viewModel.CatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CatViewModel(get()) }
}