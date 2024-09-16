package com.xsis.netplix.core.util

import com.xsis.netplix.core.exception.Failure
import com.google.gson.Gson
import com.xsis.netplix.core.data.response.GeneralErrorReponseItem
import retrofit2.HttpException

fun Throwable.getGeneralErrorServer(): Failure {
    var response : GeneralErrorReponseItem? = null
    return when (this) {
        is HttpException -> {
            try {
                response = Gson().fromJson(
                    this.response()?.errorBody()?.charStream(),
                    GeneralErrorReponseItem::class.java
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            when (this.code()) {
                400 -> {
                    if (response?.error != null) {
                        Failure.ServerError(response.error.orEmpty())
                    } else {
                        Failure.BadRequest
                    }
                }
                500 -> Failure.InternalServerError
                else -> Failure.ServerError(this.message.orEmpty())
            }
        }

        else -> {
           Failure.ServerError(this.message.orEmpty())
        }
    }
}