package com.example.task3.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task3.Status
import com.example.task3.entity.Cat
import com.example.task3.repository.CatRepositoryImpl
import com.example.task3.repository.CatRoomRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CatViewModel(
    private val repositoryRoom: CatRoomRepositoryImpl,
    val repository: CatRepositoryImpl
) :
    ViewModel() {

    private var _catListR: MutableStateFlow<List<Cat>> = MutableStateFlow(listOf())
    var catListR = _catListR.asSharedFlow()

    private var _catList: MutableStateFlow<List<Cat>> = MutableStateFlow(listOf())
    private var errosList: MutableStateFlow<String> = MutableStateFlow("")

    init {
        viewModelScope.launch {
//            val getCatsDeferred = async { getCats() }
//            val insertListInRoomDeferred = async { insertListInRoom() }
//
//            getCatsDeferred.await()
//            insertListInRoomDeferred.await()

            getCats()
            delay(3000)
            insertListInRoom()

            getList()
        }
    }

    fun insertListInRoom() {
        viewModelScope.launch(Dispatchers.Default) {
            for (i in _catList.value) {
                insert(i)
            }
        }
    }

    fun getCats() {
        viewModelScope.launch {
            when (val response = repository.getCats()) {
                is Status.Success -> _catList.value = response.data
                is Status.Error -> errosList.value = response.message
            }
        }
    }

    private fun getList() {
        viewModelScope.launch {
            repositoryRoom.catList.collectLatest {
                _catListR.value = it
            }
        }
    }

    fun insert(cat: Cat) = viewModelScope.launch(Dispatchers.IO) {
        repositoryRoom.insert(cat)
    }
}