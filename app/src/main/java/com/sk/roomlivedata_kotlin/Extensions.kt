package com.sk.roomlivedata_kotlin

import android.app.Activity
import android.view.View
import android.widget.Toast

fun View.onClick(onClick: () -> Unit) {
    setOnClickListener { onClick() }
}

fun Activity.Toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}