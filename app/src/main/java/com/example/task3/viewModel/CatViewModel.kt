package com.example.task3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task3.data.Status
import com.example.task3.entity.Cat
import com.example.task3.repository.CatRepositoryImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CatViewModel(
    private val repository: CatRepositoryImpl
) :
    ViewModel() {

    private var _catListR: MutableStateFlow<List<Cat>> = MutableStateFlow(listOf())
    var catListR = _catListR

    private var _catList: MutableStateFlow<List<Cat>> = MutableStateFlow(listOf())
    private var errorsList: MutableSharedFlow<String> = MutableSharedFlow()

    init {
        viewModelScope.launch {
            getCats()
            getList()
        }
    }

    private suspend fun getCats() {
        when (val response = repository.getCats()) {
            is Status.Success -> {
                _catList.value = response.data
                insert(_catList.value)
            }

            is Status.Error -> errorsList.emit(response.message)
        }
    }


    private suspend fun getList() {
        repository.catList.collectLatest {
            _catListR.value = it
        }
    }

    private suspend fun insert(cat: List<Cat>) = repository.insert(cat)

}