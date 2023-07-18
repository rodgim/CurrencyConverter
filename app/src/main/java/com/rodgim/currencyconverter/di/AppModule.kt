package com.rodgim.currencyconverter.di

import com.rodgim.currencyconverter.data.datasources.CurrencyRemoteDataSource
import com.rodgim.currencyconverter.data.datasources.RetrofitCurrencyDataSource
import com.rodgim.currencyconverter.data.remote.CurrencyApi
import com.rodgim.currencyconverter.other.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideCurrencyApi(
        retrofit: Retrofit
    ): CurrencyApi = retrofit.create(CurrencyApi::class.java)

    @Singleton
    @Provides
    fun provideCurrencyRemoteDataSource(
        api: CurrencyApi
    ): CurrencyRemoteDataSource = RetrofitCurrencyDataSource(api)
}