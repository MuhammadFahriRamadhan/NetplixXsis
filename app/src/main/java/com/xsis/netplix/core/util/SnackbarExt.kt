package com.xsis.netplix.core.util

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showSnackBar(view : View?,title : String,statusColor : Int){
    val snackbar = Snackbar.make(view!!, title, Snackbar.LENGTH_LONG)
    snackbar.setBackgroundTint(statusColor)
    snackbar.setTextColor(Color.WHITE)
    snackbar.addCallback(object : Snackbar.Callback() {
        override fun onShown(sb: Snackbar?) {
            super.onShown(sb)
            // Snackbar is shown
        }

        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            // Snackbar is dismissed
        }
    })
    snackbar.show()
}