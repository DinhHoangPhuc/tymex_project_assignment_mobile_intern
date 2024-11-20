package com.example.currency_converter_app.data

data class SymbolsResponse(
    val success: Boolean,
    val symbols: Map<String, String>
)
