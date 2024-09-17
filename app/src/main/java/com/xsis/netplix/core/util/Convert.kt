package com.xsis.netplix.core.util

import android.net.Uri

fun String.extractQueryParam(param: String): String? {
    val uri = Uri.parse(this)
    return uri.getQueryParameter(param)
}