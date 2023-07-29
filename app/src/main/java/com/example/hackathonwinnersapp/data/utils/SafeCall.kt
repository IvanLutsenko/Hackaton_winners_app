package com.example.hackathonwinnersapp.data.utils

import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): NetworkCallResult<T> {
    return withContext(dispatcher) {
        try {
            NetworkCallResult.Success(apiCall())
        } catch (e: IOException) {
            NetworkCallResult.Error.Network
        } catch (e: HttpException) {
            val code = e.code()
            convertErrorBody(code, e)
        }
    }
}

suspend fun <T : Any> safeApiCall(
    dispatcher: CoroutineDispatcher,
    onNullValue: () -> NetworkCallResult<T>,
    apiCall: suspend () -> T?
): NetworkCallResult<T> {
    return withContext(dispatcher) {
        try {
            val result = apiCall()

            if (result != null) NetworkCallResult.Success(result)
            else onNullValue()
        } catch (e: IOException) {
            NetworkCallResult.Error.Network
        } catch (e: HttpException) {
            val code = e.code()
            convertErrorBody(code, e)
        }
    }
}

private fun <T> convertErrorBody(code: Int, throwable: HttpException): NetworkCallResult<T> {
    return throwable.response()?.errorBody()?.let {
        parseErrorBody(it, code)
    } ?: NetworkCallResult.Error.Unknown(code)
}

private fun <T> parseErrorBody(errorBody: ResponseBody?, code: Int): NetworkCallResult<T> {
    return try {
        val errorMessage = errorBody?.string() ?: ""


        val errorJson = JsonParser.parseString(errorMessage).asJsonObject

        val statusCode =
            if (errorJson.has(STATUS_CODE)) errorJson.get(STATUS_CODE).asString
            else "error"

        val message =
            if (errorJson.has(MESSAGE)) errorJson.get(MESSAGE).asString
            else null

        NetworkCallResult.Error.General(
            statusCode = statusCode,
            message = message
        )
    } catch (e: JsonParseException) {
        NetworkCallResult.Error.Unknown(code)
    } catch (e: JsonSyntaxException) {
        NetworkCallResult.Error.Unknown(code)
    }
}

private const val STATUS_CODE = "error_code"
private const val MESSAGE = "error_message"
