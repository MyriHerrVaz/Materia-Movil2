package com.example.accesosicenet.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.accesosicenet.screens.CalificacionFinal
import com.example.accesosicenet.screens.CalificacionUnidad
import com.example.accesosicenet.screens.Cardex
import com.example.accesosicenet.screens.CardexAlumno
import com.example.accesosicenet.screens.Horario
import com.example.accesosicenet.screens.Login
import com.example.accesosicenet.screens.Perfil
import com.example.accesosicenet.viewModel.LoginView

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val viewmodel: LoginView =  viewModel(factory = LoginView.Factory)

    NavHost(navController = navController, startDestination = AppScreens.Login.ruta){
        composable(route = AppScreens.Login.ruta){
            Login(navController,viewmodel)
        }
        composable(route = AppScreens.Perfil.ruta){
            Perfil(navController,viewmodel)
        }
        composable(route = AppScreens.CalificacionFinal.ruta){
            CalificacionFinal(navController,viewmodel)
        }
        composable(route = AppScreens.CalificacionUnidad.ruta){
            CalificacionUnidad(navController,viewmodel)
        }
        composable(route = AppScreens.Cardex.ruta){
            Cardex(navController,viewmodel)
        }
        composable(route = AppScreens.CardexA.ruta){
            CardexAlumno(navController,viewmodel)
        }
        composable(route = AppScreens.Horario.ruta){
            Horario(navController,viewmodel)
        }
    }
}