package com.vishal.taghire.utils

import android.app.Activity
import com.vishal.taghire.App
import com.vishal.taghire.di.AppComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
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