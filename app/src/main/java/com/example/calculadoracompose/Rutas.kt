package com.example.calculadoracompose

sealed class Rutas(val ruta: String){
    object Inicio: Rutas("inicio")
    object Principal: Rutas("principal")
    object Adiosito: Rutas("adiosito")
}