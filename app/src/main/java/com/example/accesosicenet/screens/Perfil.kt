package com.example.accesosicenet.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.accesosicenet.Navigation.AppScreens
import com.example.accesosicenet.screens.ui.theme.AccesoSicenetTheme
import com.example.accesosicenet.viewModel.LoginView
import kotlinx.coroutines.launch

class Perfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccesoSicenetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Perfil(
    navController: NavController,
    viewmodel: LoginView =  viewModel(factory = LoginView.Factory)
){
    Scaffold(
        topBar = {
            var showMenu by remember{ mutableStateOf(false) }
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Perfil")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Atras"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Icono de menú"
                        )
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu=false }, modifier = Modifier.run { width(150.dp) }) {
                        DropdownMenuItem(text = { Text("Horario") },
                            onClick = { navController.navigate(route = AppScreens.Horario.ruta)
                            })
                        DropdownMenuItem(text = { Text("Unidades") },
                            onClick = { navController.navigate(route = AppScreens.CalificacionUnidad.ruta) })
                        DropdownMenuItem(text = { Text("Finales") },
                            onClick = { navController.navigate(route = AppScreens.CalificacionFinal.ruta) })
                        DropdownMenuItem(text = { Text("Cardex completo") },
                            onClick = { navController.navigate(route = AppScreens.CardexA.ruta) })
                        DropdownMenuItem(text = { Text("Resumen de Cardex") },
                            onClick = { navController.navigate(route = AppScreens.Cardex.ruta) })
                    }
                },
            )
        },
    ) {
        PerfilBodyContent(navController, viewmodel)
    }
}
@Composable
fun PerfilBodyContent(navController: NavController, viewmodel: LoginView){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Segunda pantalla")
        Text(
            text = "Alumno: " + viewmodel.usuario.toString()
        )
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Navega")
        }
    }
}