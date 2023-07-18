package com.rodgim.currencyconverter.data.datasources

import com.rodgim.currencyconverter.data.remote.models.CurrencyResponse

interface CurrencyRemoteDataSource {
    suspend fun getRates(base: String): Result<CurrencyResponse>
}