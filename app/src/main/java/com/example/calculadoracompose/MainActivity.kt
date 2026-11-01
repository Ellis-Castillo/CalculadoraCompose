package com.example.calculadoracompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.calculadoracompose.ui.theme.CalculadoraComposeTheme
import android.media.AudioAttributes
import android.media.SoundPool

class MainActivity : ComponentActivity() {

    //Implementación del soundPool asistido por IA.
    private lateinit var soundPool: SoundPool
    private val sonidosCargadosParaPool = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        configuracionDelSoundPool()
        cargarSonidosAlPool()

        setContent {
            var modoOscuro by remember { mutableStateOf(false) }

            CalculadoraComposeTheme(
                darkTheme = modoOscuro
            ) {
                Navegacion(
                    cambiarTema = {
                        modoOscuro = !modoOscuro
                    },
                    reproducirSilly = {
                        reproducirSilly()
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
        sonidosCargadosParaPool.clear()
    }

    private fun configuracionDelSoundPool(){
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(audioAttributes)
            .build()
    }

    private fun cargarSonidosAlPool(){
        soundPool.setOnLoadCompleteListener { _, sampleId, status ->
            if (status == 0){
                sonidosCargadosParaPool.add(sampleId)
            }
        }

        for (sonido in listaDeSonidosSillies){
            soundPool.load(this, sonido, 1)
        }
    }

    private fun reproducirSilly(){
        if (sonidosCargadosParaPool.isEmpty()) return
        val sonidoElegido = sonidosCargadosParaPool.random()
        soundPool.play(
            sonidoElegido,
            1f,
            1f,
            1,
            0,
            1f
        )
    }

    private val listaDeSonidosSillies = listOf(
        R.raw.sonido_silly_1,
        R.raw.sonido_silly_2,
        R.raw.sonido_silly_3,
        R.raw.sonido_silly_4,
        R.raw.sonido_silly_5,
        R.raw.sonido_silly_6,
        R.raw.sonido_silly_7,
        R.raw.sonido_silly_8,
        R.raw.sonido_silly_9,
        R.raw.sonido_silly_10,
        R.raw.sonido_silly_11,
        R.raw.sonido_silly_12,
        R.raw.sonido_silly_13,
        R.raw.sonido_silly_14,
        R.raw.sonido_silly_15,
        R.raw.sonido_silly_16
    )
}