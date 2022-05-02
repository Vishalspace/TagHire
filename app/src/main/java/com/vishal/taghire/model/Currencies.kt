package com.vishal.taghire.model

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("baseAsset") val baseAsset: String,
    @SerializedName("quoteAsset") val quoteAsset: String,
    @SerializedName("openPrice") val openPrice: Double,
    @SerializedName("lowPrice") val lowPrice: Double,
    @SerializedName("highPrice") val highPrice: Double,
    @SerializedName("lastPrice") val lastPrice: String,
    @SerializedName("volume") val volume: Double,
    @SerializedName("bidPrice") val bidPrice: Double,
    @SerializedName("askPrice") val askPrice: Double,
    @SerializedName("at") val at: Long,
)