package com.rodgim.currencyconverter.data.remote.models

data class CurrencyResponse(
    val base: String,
    val date: String,
    val rates: Rates
)
