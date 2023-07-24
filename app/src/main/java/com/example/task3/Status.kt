package com.example.task3

sealed class Status<out T> {
    data class Success<T>(val data: T) : Status<T>()
    data class Error(val message: String, val code: Int) : Status<Nothing>()
}