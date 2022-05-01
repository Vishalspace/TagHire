package com.vishal.taghire.api

import com.vishal.taghire.model.Currency
import io.reactivex.Single
import retrofit2.http.GET

interface WazirApi {
    @GET("24hr")
    fun getQuotes() : Single<List<Currency>>

//    @GET("24hr?symbol=btcinr")
//    fun getCurrency() : Single<Symbol>
}