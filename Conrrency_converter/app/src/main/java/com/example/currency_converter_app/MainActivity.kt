package com.example.currency_converter_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currency_converter_app.ui.theme.CurrencyConverterAppTheme
import com.example.currency_converter_app.view_model.CurrencyViewModel
import kotlinx.coroutines.selects.select

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConverterAppTheme {
                Surface {
                    CurrencyConverterScreen()
                }
            }
        }
    }
}

//compose function for the main screen
@Composable
fun CurrencyConverterScreen(viewModel: CurrencyViewModel = viewModel()) {
    //due to the API limitation of free plan, the base currency is fixed to EUR
    var baseCurrency by rememberSaveable { mutableStateOf("EUR") }

    var targetCurrency by rememberSaveable { mutableStateOf("") }
    var inputAmount by rememberSaveable { mutableStateOf("") }
    val currencies by viewModel.symbols.observeAsState(emptyMap<String, String>())
    val outputAmount by viewModel.outputAmount.observeAsState("")
    val errorMessage by viewModel.errorMessage.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //base currency dropdown menu
        //due to the API limitation of free plan, the base currency is fixed to EUR
        SingleDropdownMenuCurrency(
            label = "Base Currency",
            selectedText = baseCurrency,
            currencies = currencies.keys.toList(),
            selectedCurrency = { baseCurrency = it },
            disabeled = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        //target currency dropdown menu
        SingleDropdownMenuCurrency(
            label = "Target Currency",
            selectedText = targetCurrency,
            currencies = currencies.keys.toList(),
            selectedCurrency = { selected -> targetCurrency = selected }
        )
        Spacer(modifier = Modifier.height(16.dp))

        //input amount text field
        TextField(
            value = inputAmount,
            onValueChange = {
                inputAmount = it
            },
            label = { Text("Input Amount") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        //output amount text field
        TextField(
            value = outputAmount,
            onValueChange = {  },
            label = { Text("Output Amount") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        Spacer(modifier = Modifier.height(16.dp))

        //convert button
        Button(onClick = {
            viewModel.convert(baseCurrency, targetCurrency, inputAmount)
        }) {
            Text("Convert")
        }

        //display error message
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = errorMessage, color = Color.Red)
        }
    }
}

//compose function for dropdown menu
@Composable
fun SingleDropdownMenuCurrency(
    disabeled: Boolean = false,
    label: String,
    currencies: List<String> = listOf("USD", "EUR", "JPY", "GBP", "AUD"),
    selectedText: String,
    selectedCurrency: (String) -> Unit = {}
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    var selectedText by rememberSaveable { mutableStateOf(selectedText) }

    val SizeSaver = Saver<Size, List<Float>>(
        save = { listOf(it.width, it.height) },
        restore = { Size(it[0], it[1]) }
    )

    var textfieldSize by rememberSaveable(stateSaver = SizeSaver) { mutableStateOf(Size.Zero) }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            value = selectedText,
            onValueChange = { selectedText = it },
            label = {
                Text(label)
            },
            trailingIcon = {
                Icon(
                    icon,
                    contentDescription = "contentDescription",
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            enabled = !disabeled
        )

        //if the dropdown menu is not disabled, show the dropdown menu
        if(!disabeled) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){textfieldSize.width.toDp()})
            ) {
                currencies.forEach() { label ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = label
                            )
                        },
                        onClick = {
                            expanded = false
                            selectedText = label
                            selectedCurrency(label)
                        })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyConverterScreenPreview() {
    CurrencyConverterAppTheme {
        CurrencyConverterScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun SingleDropdownMenuPreview() {
    CurrencyConverterAppTheme {
        SingleDropdownMenuCurrency(
            label = "Base Currency",
            currencies = listOf("USD", "EUR", "JPY", "GBP", "AUD"),
            selectedText = "USD",
            selectedCurrency = {},
            disabeled = true
        )
    }
}