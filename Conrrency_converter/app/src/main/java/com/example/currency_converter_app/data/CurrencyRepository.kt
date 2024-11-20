package com.example.currency_converter_app.data

class CurrencyRepository {
    private val currencyService = RetrofitInstance.getLatestRates

    suspend fun getRate(): CurrencyResponse {
        return currencyService.getRate().body()!!
    }

    suspend fun getSymbols(): SymbolsResponse {
        return currencyService.getSymbols().body()!!
    }
}