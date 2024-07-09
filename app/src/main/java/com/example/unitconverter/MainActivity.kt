package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var isExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor by remember { (mutableStateOf(1.00)) }
    var oConversionFactor by remember {
        mutableStateOf(
            1.00
        )
    }

    fun converter(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor * 100.0/ oConversionFactor).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.headlineLarge)
        //Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange =  {
            inputValue = it
        }, label = {
            Text(text = "Enter Text")
            })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
          Box{
              Button(onClick = {
                  isExpanded = true
              }) {
                  Text(inputUnit)
                  Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
              }
              DropdownMenu(expanded = isExpanded, onDismissRequest = {
                  isExpanded = false
              }) {
                  DropdownMenuItem(text = { Text("CentiMeters") }, onClick = {
                      isExpanded = false
                      inputUnit = "Centimeters"
                      conversionFactor = 0.01
                      converter()
                  })
                  DropdownMenuItem(text = { Text("Meters") }, onClick = {
                      isExpanded = false
                      inputUnit = "Meters"
                      conversionFactor = 1.0
                      converter()
                  })
                  DropdownMenuItem(text = { Text("Feet") }, onClick = {
                      isExpanded = false
                      inputUnit = "Feet"
                      conversionFactor = 0.3048
                      converter()
                  })
                  DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                      isExpanded = false
                      inputUnit = "MilliMeters"
                      conversionFactor = 0.001
                      converter()
                  })
              }
          }
            Spacer(modifier = Modifier.width(16.dp))

            Box{

                Button(onClick = {
                    oExpanded = true
                }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {
                    oExpanded = false
                }) {
                    DropdownMenuItem(text = { Text("CentiMeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeters"
                        oConversionFactor = 0.01
                        converter()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Meters"
                        oConversionFactor = 1.0
                        converter()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oConversionFactor = 0.3048
                        converter()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Millimeters"
                        oConversionFactor = 0.001
                        converter()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverter()
}