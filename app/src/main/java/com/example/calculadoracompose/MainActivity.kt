package com.example.calculadoracompose

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.calculadoracompose.ui.theme.CalculadoraComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraComposeTheme {

            }
        }
    }
}

@Composable
fun PantallaPrincipal(){
    //var historial by rememberSaveable { mutableStateOf("") }
    var operacionMostrada by rememberSaveable { mutableStateOf("") }
    //var operandoPrimero by remember { mutableDoubleStateOf(0.0) }
    //var operandoSegundo by remember { mutableDoubleStateOf(0.0) }
    //var resultado by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Opciones()
        ResultadoHistorial(
            operacionMostrada = operacionMostrada
        )
        Teclado(
            operacionMostrada = operacionMostrada,
            cambioOperacion = {operacionMostrada = it})
    }
}

@Composable
fun Opciones(){
    val teclas = listOf("i","☾","←","%")

    Row() {
        Button() {
            Text(
                teclas[0]
            )
        }
        Button() {
            Text(
                teclas[1]
            )
        }
        Button() {
            Text(
                teclas[2]
            )
        }
        Button() {
            Text(
                teclas[3]
            )
        }
    }
}

@Composable
fun ResultadoHistorial(
    operacionMostrada: String,
    //historial: String,
){
    //Text(historial)
    Text(
        text = operacionMostrada,
        modifier = Modifier
            .padding(bottom = 4.dp)
    )
}

/*
@Composable
fun Calculo(){}
*/

/*
@Composable
fun Resultado(){}

@Composable
fun Historial(){}
 */

@Composable
fun Teclado(
    operacionMostrada: String,
    cambioOperacion: (String) -> Unit
){
    val teclas = listOf(
        "7","8","9","/",
        "4","5","6","x",
        "1","2","3","-",
        ",","0","=","+"
    )

    Column() {
        Row() {
            Boton(teclas[0]) {
                operacionMostrada += teclas[0]
            }

            Button() {
                Text(
                    teclas[1]
                )
            }
            Button() {
                Text(
                    teclas[2]
                )
            }
            Button() {
                Text(
                    teclas[3]
                )
            }
        }
        Row() {
            Button() {
                Text(
                    teclas[4]
                )
            }
            Button() {
                Text(
                    teclas[5]
                )
            }
            Button() {
                Text(
                    teclas[6]
                )
            }
            Button() {
                Text(
                    teclas[7]
                )
            }
        }
        Row() {
            Button() {
                Text(
                    teclas[8]
                )
            }
            Button() {
                Text(
                    teclas[9]
                )
            }
            Button() {
                Text(
                    teclas[10]
                )
            }
            Button() {
                Text(
                    teclas[11]
                )
            }
        }
        Row() {
            Button() {
                Text(
                    teclas[12]
                )
            }
            Button() {
                Text(
                    teclas[13]
                )
            }
            Button() {
                Text(
                    teclas[14]
                )
            }
            Button() {
                Text(
                    teclas[15]
                )
            }
        }
    }
}

@Composable
fun Boton(
    simbolo: String,
    onClick: () -> Unit
){
    Button(onClick = onClick) {
        Text(simbolo)
    }
}

@Composable
fun PantallaEntrada(){}

@Composable
fun PantallaSalida(){}
