package com.example.calculadoracompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun PantallaAdiosito(){
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(2000.milliseconds)
        (context as? Activity)?.finish()
        //POR QUÉ ANDROIDSTUDIO NO DETIENE LA APP CUANDO SE FINALIZA EN EL EMULADOR????
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .fillMaxSize()
                    .weight(3f)
                    .align(Alignment.CenterHorizontally)
            ){
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = "¡Hasta la próxima!",
                    fontSize = 52.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .offset(y = 12.dp)
                )
            }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .fillMaxSize()
                    .weight(4f)
            ){}
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                //.offset(y = 100.dp)
                .padding(horizontal = 60.dp, vertical = 44.dp)
        ) {
            Image(
                contentDescription = "",
                painter = painterResource(R.drawable.logo_app),
                modifier = Modifier
                    .size(140.dp)
            )
        }
    }
}