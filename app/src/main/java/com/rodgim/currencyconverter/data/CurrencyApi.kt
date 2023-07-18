package com.rodgim.currencyconverter.data

import com.rodgim.currencyconverter.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("/v1/latest")
    suspend fun getRates(
        @Query("base") base: String,
        @Query("access_key") apiKey: String = BuildConfig.API_KEY
    )
}
