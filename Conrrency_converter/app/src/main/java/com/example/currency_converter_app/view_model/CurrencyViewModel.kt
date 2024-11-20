package com.example.currency_converter_app.view_model

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currency_converter_app.data.CurrencyRepository
import com.example.currency_converter_app.data.CurrencyResponse
import kotlinx.coroutines.launch
import java.io.IOException

class CurrencyViewModel : ViewModel() {
    private val repository = CurrencyRepository()

    private val _conversion = MutableLiveData<CurrencyResponse?>()

    private val _symbols = MutableLiveData<Map<String, String>>()
    val symbols: LiveData<Map<String, String>> = _symbols

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    private val _outputAmount = MutableLiveData("")
    val outputAmount: LiveData<String> = _outputAmount

    // Initialize the ViewModel by loading symbols and rates
    init {
        getSymbols()
        getRates()
    }

    // Function to perform currency conversion
    fun convert(baseCurrency: String, targetCurrency: String, inputAmount: String) {
        if(validateInput(baseCurrency, targetCurrency, inputAmount)) {
            val amount = inputAmount.toFloatOrNull()
            val conversionRate = _conversion.value!!.rates[targetCurrency]

            if(validateRateAndAmount(conversionRate!!, amount!!)) {
                viewModelScope.launch {
                    try {
                        _outputAmount.value = calculateOutputAmount(amount, conversionRate).toString()
                        Log(_conversion.value!!, baseCurrency, targetCurrency)
                    } catch (e: Exception) {
                        _errorMessage.value = "Conversion failed: ${e.message}"
                        Log.e("CurrencyViewModel", "Conversion failed", e)
                    }
                }
            }
        }
    }

    // Function to load currency symbols from the repository
    private fun getSymbols() {
        viewModelScope.launch {
            try {
                _symbols.value = repository.getSymbols().symbols
            } catch (e: IOException) {
                _errorMessage.value = "No internet connection."
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load symbols: ${e.message}"
            }
        }
    }

    // Function to load currency rates from the repository
    private fun getRates() {
        viewModelScope.launch {
            try {
                _conversion.value = repository.getRate()
            } catch (e: IOException) {
                _errorMessage.value = "No internet connection."
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load rates: ${e.message}"
            }
        }
    }

    // Function to calculate the output amount based on the input amount and conversion rate
    private fun calculateOutputAmount(inputAmount: Float, conversionRate: Float): Float {
        return inputAmount * conversionRate
    }

    // Function to validate the input values
    private fun validateInput(baseCurrency: String, targetCurrency: String, inputAmount: String): Boolean {
        if (baseCurrency.isEmpty() || targetCurrency.isEmpty()) {
            _errorMessage.value = "Please select valid currencies."
            return false
        } else if (_symbols.value?.get(baseCurrency) == null || _symbols.value?.get(targetCurrency) == null) {
            _errorMessage.value = "Please select valid currencies."
            return false
        } else if (inputAmount.isEmpty() || inputAmount.toFloatOrNull() == null) {
            _errorMessage.value = "Please enter a valid amount."
            return false
        }
        return true
    }

    // Function to validate the conversion rate and input amount
    private fun validateRateAndAmount(rate: Float, amount: Float): Boolean {
        if (rate <= 0) {
            _errorMessage.value = "Invalid conversion rate."
            return false
        } else if (amount <= 0) {
            _errorMessage.value = "Invalid amount."
            return false
        }
        return true
    }

    private fun Log(currencyResponse: CurrencyResponse, baseCurrency: String, targetCurrency: String) {
        Log.d("CurrencyViewModel", "Conversion: $currencyResponse")
        Log.d("BaseCurrency", baseCurrency)
        Log.d("TargetCurrency", targetCurrency)
    }
}
