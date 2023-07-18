package com.rodgim.currencyconverter.data.datasources

import com.rodgim.currencyconverter.data.remote.CurrencyApi
import com.rodgim.currencyconverter.data.remote.models.CurrencyResponse
import javax.inject.Inject

class RetrofitCurrencyDataSource @Inject constructor(
    private val api: CurrencyApi
) : CurrencyRemoteDataSource {
    override suspend fun getRates(base: String): Result<CurrencyResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Result.success(result)
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}