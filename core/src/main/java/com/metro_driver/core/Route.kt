package com.metro_driver.core

import android.app.Activity
import android.content.Context
import android.content.Intent

abstract class Route(private val context: Context) {
    abstract fun destination(): Class<*>
    fun navigate(allowBack: Boolean = true) {
        context.startActivity(
            Intent(context, destination())
        )
        if (!allowBack) (context as Activity).finish()
    }
}