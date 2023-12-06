package com.example.ktorandroidclient.utils

import android.content.Intent
import android.os.Build
import java.io.Serializable

/**
 * Used to get serializable data from intent irrespective to Android version
 */
@Suppress("DEPRECATION")
fun <T : Serializable> Intent.getSerializableData(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}

