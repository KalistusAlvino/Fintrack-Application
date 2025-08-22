package com.example.fintrack.core.common

sealed class ResultState<T>(val data:  T?, val message : String?=null){
    class Success<T> (data: T?) : ResultState<T>(data)
    class Loading<T>(data: T?= null) : ResultState<T>(data)
    class Error<T>(data: T? = null, message: String?) : ResultState<T>(data,message)
}