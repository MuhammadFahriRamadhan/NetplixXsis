package com.xsis.netplix.core.util

import android.app.Activity
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import com.xsis.netplix.R

fun LayoutInflater.cloneDefaultTheme(activity: Activity): LayoutInflater {
    val contextThemeWrapper = ContextThemeWrapper(activity, R.style.Base_Theme_NetplixXsis)
    return this.cloneInContext(contextThemeWrapper)
}