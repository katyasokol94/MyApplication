package com.sokolkatya.myapplication.extension

import android.content.Context

fun Context.dipI(value: Float): Int = (value * resources.displayMetrics.density).toInt()