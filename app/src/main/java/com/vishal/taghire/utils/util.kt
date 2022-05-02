package com.vishal.taghire.utils

import android.app.Activity
import com.vishal.taghire.App
import com.vishal.taghire.di.AppComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat
import java.util.*


fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun Activity.injector(): AppComponent {
    return (application as App).component
}

fun Long.toHour(): String {
    return Date(this).hours.toString()
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd/MMM/yy HH:mm:ss")
    return format.format(date)
}
