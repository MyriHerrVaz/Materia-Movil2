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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.accesosicenet.Navigation.AppScreens
import com.example.accesosicenet.data.AppContainer
import com.example.accesosicenet.data.Workers.workerFinal
import com.example.accesosicenet.screens.ui.theme.AccesoSicenetTheme
import com.example.accesosicenet.viewModel.LoginView
import com.example.accesosicenet.viewModel.WorkerAccessState
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
                            onClick = {
                                if(viewmodel.Internert){
                                    viewmodel.presionarCarga = true;
                                }else{
                                    navController.navigate(route = AppScreens.Horario.ruta)
                                }
                                 })
                        DropdownMenuItem(text = { Text("Unidades") },
                            onClick = {
                                if(viewmodel.Internert){
                                    viewmodel.presionarUnidad = true;
                                }else{
                                    navController.navigate(route = AppScreens.CalificacionUnidad.ruta)
                                }
                                 })
                        DropdownMenuItem(text = { Text("Finales") },
                            onClick = {
                                if(viewmodel.Internert){
                                    viewmodel.presionarFinal = true;
                                }else{
                                    navController.navigate(route = AppScreens.CalificacionFinal.ruta)
                                }
                                 })
                        DropdownMenuItem(text = { Text("Cardex completo") },
                            onClick = {
                                if(viewmodel.Internert){
                                    viewmodel.presionarCardex = true;
                                }else{
                                    navController.navigate(route = AppScreens.Cardex.ruta)
                                }
                                })
                        DropdownMenuItem(text = { Text("Resumen de Cardex") },
                            onClick = {
                                if(viewmodel.Internert){
                                    viewmodel.presionarCardexR = true;
                                }else{
                                    navController.navigate(route = AppScreens.CardexA.ruta)
                                }
                            })
                    }
                },
            )
        },
    ) {
        PerfilBodyContent(navController, viewmodel)
    }
}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PerfilBodyContent(navController: NavController, viewmodel: LoginView){
    val uiStateFinal by viewmodel.workerUiStateFinales.collectAsStateWithLifecycle()
    val uiStateUnidad by viewmodel.workerUiStateUnidades.collectAsStateWithLifecycle()
    val uiStateCargaAcademica by viewmodel.workerUiStateCargaAcademica.collectAsStateWithLifecycle()
    val uiStateCardex by viewmodel.workerUiStateCardex.collectAsStateWithLifecycle()
    val uiStateCardex2 by viewmodel.workerUiStateCardex2.collectAsStateWithLifecycle()
    val uiStateCardexR by viewmodel.workerUiStateCardexR.collectAsStateWithLifecycle()
    val corrutina = rememberCoroutineScope()
    if(viewmodel.Internert){
        viewmodel.getCargaAcademica()
        viewmodel.getCalificacionFinal()
        viewmodel.getCalificacionUnidad()
        viewmodel.getCardex()
    }else{
        corrutina.launch {
            viewmodel.getCardexDB()
            viewmodel.getCargaAcademicaDB()
            viewmodel.getFinalesDB()
            viewmodel.getCalificacionUnidad()
        }
    }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Perfil del usuario")
        Text(
            text = "Fecha: "+viewmodel.UsuarioInfo.fecha + "\nAlumno: "+viewmodel.UsuarioInfo.toString()
        )
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Atras")
        }
    }
    when (uiStateFinal) {
        is WorkerAccessState.Default -> {
        }
        is WorkerAccessState.Loading -> {
        }
        is WorkerAccessState.Complete -> {
            viewmodel.mostrarFinales((uiStateFinal as WorkerAccessState.Complete).outputUno)
            if(viewmodel.presionarFinal && viewmodel.Internert){
                viewmodel.presionarFinal = false;
                navController.navigate(route = AppScreens.CalificacionFinal.ruta)
            }
        }
    }
    when (uiStateUnidad) {
        is WorkerAccessState.Default -> {
        }
        is WorkerAccessState.Loading -> {
        }
        is WorkerAccessState.Complete -> {
            viewmodel.mostrarUnidades((uiStateUnidad as WorkerAccessState.Complete).outputUno)
            if(viewmodel.presionarUnidad && viewmodel.Internert){
                viewmodel.presionarUnidad = false;
                navController.navigate(route = AppScreens.CalificacionUnidad.ruta)
            }

        }
    }
    when (uiStateCargaAcademica) {
        is WorkerAccessState.Default -> {
        }
        is WorkerAccessState.Loading -> {
        }
        is WorkerAccessState.Complete -> {
            viewmodel.mostrarCargaAcademica((uiStateCargaAcademica as WorkerAccessState.Complete).outputUno)
            if(viewmodel.presionarCarga && viewmodel.Internert){
                viewmodel.presionarCarga = false;
                navController.navigate(route = AppScreens.Horario.ruta)
            }

        }
    }
    when (uiStateCardex) {
        is WorkerAccessState.Default -> {
        }
        is WorkerAccessState.Loading -> {
        }
        is WorkerAccessState.Complete -> {

                if (viewmodel.Cardex1Act == false && viewmodel.Internert) {
                    viewmodel.limpiarKardex()
                    viewmodel.mostrarCardex1((uiStateCardex as WorkerAccessState.Complete).outputUno)
                }
        }
    }
    when (uiStateCardex2) {
        is WorkerAccessState.Default -> {
        }
        is WorkerAccessState.Loading -> {
        }
        is WorkerAccessState.Complete -> {
            if(viewmodel.Cardex1Fin){
                if(viewmodel.Cardex2Act==false && viewmodel.Internert){
                    viewmodel.mostrarCardex2((uiStateCardex2 as WorkerAccessState.Complete).outputUno)
                    if(viewmodel.presionarCardex){
                        viewmodel.presionarCardex = false;
                        navController.navigate(route = AppScreens.CardexA.ruta)
                    }

                }
            }
        }
    }
    when (uiStateCardexR) {
        is WorkerAccessState.Default -> {
        }
        is WorkerAccessState.Loading -> {
        }
        is WorkerAccessState.Complete -> {
            if(viewmodel.Cardex1Fin && viewmodel.Cardex2Fin && viewmodel.Internert){
                viewmodel.mostrarCardexR((uiStateCardexR as WorkerAccessState.Complete).outputUno)
                if(viewmodel.presionarCardexR){
                    viewmodel.presionarCardexR = false;
                    navController.navigate(route = AppScreens.Cardex.ruta)
                }
            }
        }
    }
}