package com.vishal.taghire.di

import com.vishal.taghire.MainActivity
import com.weather.di.ContextModule
import com.weather.di.NetModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ContextModule::class, NetModule::class])
interface AppComponent {
    fun inject(ma: MainActivity)

}