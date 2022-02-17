package com.shapes.watch.common

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val exception: Exception) : Resource<T>()
    class Loading<T> : Resource<T>()
}
