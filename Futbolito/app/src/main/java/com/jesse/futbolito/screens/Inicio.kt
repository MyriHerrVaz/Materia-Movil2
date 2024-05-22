package com.jesse.futbolito.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jesse.futbolito.navegacion.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InicioScreen( navController: NavController){
    Scaffold(
        topBar ={
            TopAppBar(title = {"inicio"})
        }
    ){
        InicioBodyContent(navController)
    }
}

@Composable
fun InicioBodyContent( navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¿Quieres jugar?")
        Button(onClick = {
            navController.navigate(route = AppScreen.JuegoScreen.route)
        }) {
            Text(text = "Jugar")
        }
    }
}