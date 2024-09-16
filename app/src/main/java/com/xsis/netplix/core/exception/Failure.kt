package com.xsis.netplix.core.exception

sealed class Failure {
    data object NetworkConnection : Failure()
    data object BadRequest : Failure()
    data object InternalServerError : Failure()
    data class ServerError(val message: String) : Failure()
}