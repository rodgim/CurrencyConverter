package com.rodgim.currencyconverter.data

import com.rodgim.currencyconverter.data.datasources.CurrencyRemoteDataSource
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyRemoteDataSource: CurrencyRemoteDataSource
) {
    suspend fun getRates(base: String) = currencyRemoteDataSource.getRates(base)
}