package com.example.currency_converter_app.data

import com.example.currency_converter_app.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface CurrencyApi {

    @GET("latest")
    suspend fun getRate(
        @Query("access_key") access_key: String = BuildConfig.CURRENCY_API_ACCESS_KEY,
    ): Response<CurrencyResponse>

    @GET("symbols")
    suspend fun getSymbols(
        @Query("access_key") access_key: String = BuildConfig.CURRENCY_API_ACCESS_KEY
    ): Response<SymbolsResponse>
}