package com.jesse.futbolito.navegacion



sealed class AppScreen(val route: String) {
    object InicioScreen: AppScreen(route = "Inicio")
    object JuegoScreen: AppScreen(route = "Juego")
}