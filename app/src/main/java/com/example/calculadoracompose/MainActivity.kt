package com.example.calculadoracompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.calculadoracompose.ui.theme.CalculadoraComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var modoOscuro by remember { mutableStateOf(false) }

            CalculadoraComposeTheme(
                darkTheme = modoOscuro
            ) {
                PantallaPrincipal(
                    modoOscuro = modoOscuro,
                    cambiarTema = {
                        modoOscuro = !modoOscuro
                    }
                )
            }
        }
    }
}

@Composable
fun PantallaPrincipal(
    modoOscuro: Boolean,
    cambiarTema: () -> Unit
){
    //var historial by rememberSaveable { mutableStateOf("") }
    var operacionMostrada by rememberSaveable { mutableStateOf("") }
    //var operandoPrimero by remember { mutableDoubleStateOf(0.0) }
    //var operandoSegundo by remember { mutableDoubleStateOf(0.0) }
    //var resultado by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 20.dp)
    ) {
        //Spacer(Modifier.height(20.dp).background(MaterialTheme.colorScheme.background))
        Opciones(
            operacionMostrada = operacionMostrada,
            cambioOperacion = {operacionMostrada = it},
            cambiarTema = cambiarTema,
            modifier = Modifier
                .weight(1f)
        )
        ResultadoHistorial(
            operacionMostrada = operacionMostrada,
            modifier = Modifier
                .weight(2f)
        )
        Teclado(
            operacionMostrada = operacionMostrada,
            cambioOperacion = {operacionMostrada = it},
            modifier = Modifier
                .weight(5f)
        )
        //Spacer(Modifier.height(20.dp).background(MaterialTheme.colorScheme.background))
    }
}

@Composable
fun Opciones(
    operacionMostrada: String,
    cambioOperacion: (String) -> Unit,
    cambiarTema: () -> Unit,
    modifier: Modifier = Modifier
){
    val teclas = listOf("i","T","←","%")
    var mostrarMensaje = false

    Row(
        modifier = modifier
            .fillMaxSize()
    ) {
        Boton(
            simbolo = teclas[0],
            onClick = {
                mostrarMensaje = true
            },
            modifier = Modifier
                .weight(1f)
        )
        Button(
            onClick = cambiarTema,
            shape = RectangleShape,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline
            ),
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Text(teclas[1])
        }
        Boton(
            simbolo = teclas[2],
            onClick = {
                cambioOperacion(operacionMostrada.dropLast(1))
            },
            modifier = Modifier
                .weight(1f)
        )
        Boton(
            simbolo = teclas[3],
            onClick = {
                cambioOperacion(operacionMostrada + teclas[3])
            },
            modifier = Modifier
                .weight(1f)
        )

        if (mostrarMensaje){
            MensajeInfo()
            mostrarMensaje = false
        }
    }
}

@Composable
fun ResultadoHistorial(
    operacionMostrada: String,
    //historial: String,
    modifier: Modifier = Modifier
){
    //Text(historial)
    Text(
        text = operacionMostrada,
        modifier = modifier
            .padding(vertical = 8.dp)
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
    cambioOperacion: (String) -> Unit,
    modifier: Modifier = Modifier
){
    val teclasSinIgual = listOf(
        listOf("7","8","9","/"),
        listOf("4","5","6","x"),
        listOf("1","2","3","-")
    )
    val teclasEspeciales = listOf(",","0","=","+")

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        for (fila in teclasSinIgual){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ){
                for (tecla in fila){
                    Boton(
                        simbolo = tecla,
                        onClick = {
                            cambioOperacion(operacionMostrada + tecla)
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            for (tecla in teclasEspeciales){
                if (tecla == "="){
                    Boton(
                        simbolo = tecla,
                        onClick = {
                            cambioOperacion("")
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                } else if (tecla == ","){
                    Boton(
                        simbolo = tecla,
                        onClick = {
                            if (!operacionMostrada.contains(","))
                                {cambioOperacion(operacionMostrada + tecla)}
                            else
                                {cambioOperacion(operacionMostrada)}
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                } else{
                    Boton(
                        simbolo = tecla,
                        onClick = {
                            cambioOperacion(operacionMostrada + tecla)
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun MensajeInfo(){
    val contexto = LocalContext.current
    Toast.makeText(
        contexto,
        "Soy información, Yeiii",
        Toast.LENGTH_LONG
    ).show()
}

@Composable
fun Boton(
    simbolo: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxSize(),
        shape = RectangleShape,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Text(simbolo)
    }
}

@Composable
fun PantallaEntrada(){}

@Composable
fun PantallaSalida(){}
