package com.example.gitlookup.utils

import com.example.gitlookup.models.Result
import com.example.gitlookup.network.MSG_RESPONSE_IS_NULL
import kotlinx.coroutines.yield
import retrofit2.Response

suspend fun <T> apiHandler(call: suspend () -> Response<T>): Result<T> {
    yield()
    val response = call.invoke()
    return if (response.isSuccessful) {
        response.body()?.let {
            Result.Success(it)
        } ?: run {
            Result.Error(Exception(MSG_RESPONSE_IS_NULL))
        }
    } else {
        val error = response.errorBody()?.charStream().toString()
        Result.Error(Exception(error))
    }
}