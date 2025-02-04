package eu.tutorials.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme
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
@Composable
fun UnitConverter() {
    var inputvalue by remember {mutableStateOf("") }
    var outputvalue by remember {mutableStateOf("") }
    var inputunit by remember {mutableStateOf("Centimeters") }
    var outputunit by remember {mutableStateOf("Meters")}
    var iexpanded by remember {mutableStateOf(false)}
    var oexpanded by remember {mutableStateOf(false)}
    var iconversionFactor = remember {mutableStateOf(1.00)}
    var oconversionFactor = remember {mutableStateOf(1.00)}

    fun Convertunits(){
        val inputvaluedouble = inputvalue.toDoubleOrNull()?:0.0
        val result = (inputvaluedouble * iconversionFactor.value * 100.0 /oconversionFactor.value).roundToInt() /100.0
        outputvalue = result.toString()
    }




    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        here all the UI stuff will be stacked below each other
        Text(text = "Unit Converter", modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium

            )
//        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue, 
            onValueChange = {
                inputvalue = it
                Convertunits()
            //here goes something that should be executed ,when the value of our outlinedinputfield changes})
        }, 
            label = { Text(text = "Enter Value ")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
//            here all thew UI stuff will be stacked next to each other

            Box {
//                input button
                Button(onClick = { iexpanded = true }) {
                    Text(text = inputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded = false}) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            iexpanded = false
                            inputunit = "Centimeters"
                            iconversionFactor.value = 0.01
                            Convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            iexpanded = false
                            inputunit = "Meters"
                            iconversionFactor.value = 1.0
                            Convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            iexpanded = false
                            inputunit = "Feet"
                            iconversionFactor.value =0.3048
                            Convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimeters") },
                        onClick = {
                            iexpanded = false
                            inputunit = "Milimeters"
                            iconversionFactor.value = 0.001
                            Convertunits()
                        }
                    )
                }
            }


            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oexpanded = true }) {
                    Text(text = outputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = { oexpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            oexpanded = false
                            outputunit = "Centimeters"
                            oconversionFactor.value = 0.01
                            Convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            oexpanded = false
                            outputunit = "Meters"
                            oconversionFactor.value = 1.00
                            Convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            oexpanded = false
                            outputunit = "Feet"
                            oconversionFactor.value = 0.3048
                            Convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimeters") },
                        onClick = {4
                            oexpanded = false
                            outputunit = "Milimeters"
                            oconversionFactor.value = 0.001
                            Convertunits()
                        }
                    )
                }
            }
        }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Result : $outputvalue $outputunit",
                style = MaterialTheme.typography.headlineMedium
            )


    }


}
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
