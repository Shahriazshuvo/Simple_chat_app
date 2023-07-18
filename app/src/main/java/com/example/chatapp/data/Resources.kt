package com.example.chatapp.data

sealed class Resources<out R> {
    data class Success<out R>(val result: R) : Resources<R>()
    data class Failure<out R>(val exception: Exception) : Resources<R>()
    object Loading : Resources<Nothing>()
}