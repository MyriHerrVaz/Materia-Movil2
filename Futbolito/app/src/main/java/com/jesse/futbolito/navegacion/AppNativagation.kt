package com.jesse.futbolito.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jesse.futbolito.screens.InicioScreen
import com.jesse.futbolito.screens.JuegoScreen

@Composable
fun AppNavigation(){
    val navContorller = rememberNavController()
    NavHost(navController = navContorller, startDestination = AppScreen.InicioScreen.route) {
        composable(AppScreen.InicioScreen.route){
            InicioScreen(navContorller)
        }
        composable(AppScreen.JuegoScreen.route){
            JuegoScreen(navContorller)
        }

    }
}