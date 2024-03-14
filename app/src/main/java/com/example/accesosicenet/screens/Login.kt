package com.example.accesosicenet.screens


import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.accesosicenet.Navigation.AppScreens
import com.example.accesosicenet.R
import com.example.accesosicenet.viewModel.LoginView
import com.example.accesosicenet.viewModel.WorkerAccessState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Login(navController: NavController,viewmodel: LoginView){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Login")
                }
            )
        },
    ){
        BodyContent(navController,viewmodel)
    }
}

@Composable
fun BodyContent(navController: NavController,viewmodel: LoginView){
    val uiState by viewmodel.workerUiStateAccess.collectAsStateWithLifecycle()
    val corrutina = rememberCoroutineScope()
    val context = LocalContext.current
    var ingresar by remember{ mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Escribe el usuario!",
            modifier = Modifier
        )
        NameField(viewmodel,modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth()
        )
        Text(
            text = "Contraseña:",
            modifier = Modifier
        )
        PassField(viewmodel,modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth()
        )
        Button(onClick = {
            if (!viewmodel.nocontrol.equals("") && !viewmodel.password.equals("")){
                corrutina.launch {
                    viewmodel.Internert=existeConexionInternet(context)
                    if(viewmodel.Internert){
                        viewmodel.getAccess()
                    } else{
                        if(viewmodel.getAccessDB()){
                            navController.navigate(route = AppScreens.Perfil.ruta)
                        }
                    }
                }
                    ingresar = !ingresar
                    viewmodel.getAccess()
            }
        }) {
            Text(stringResource(R.string.boton))
        }
        when (uiState) {
            is WorkerAccessState.Default -> {
            }
            is WorkerAccessState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(10.dp))
            }
            is WorkerAccessState.Complete -> {
                    if(ingresar && viewmodel.AccesoApp((uiState as WorkerAccessState.Complete).outputUno,
                            (uiState as WorkerAccessState.Complete).outputDos)){
                        ingresar = !ingresar
                        navController.navigate(route = AppScreens.Perfil.ruta)
                    }
                }
            }
        }
    }

@Composable
fun NameField(viewmodel: LoginView, modifier: Modifier = Modifier) {
    TextField(
        value = viewmodel.nocontrol,
        onValueChange = { viewmodel.nocontrol = it},
        singleLine = true,
        label = { Text(stringResource(R.string.usuario)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = modifier
    )
}
@Composable
fun PassField(viewmodel: LoginView, modifier: Modifier = Modifier) {
    TextField(
        value = viewmodel.password,
        onValueChange = { viewmodel.password = it},
        singleLine = true,
        label = { Text(stringResource(R.string.pass)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = modifier
    )
}
suspend fun existeConexionInternet(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

    if (networkCapabilities != null) {
        // Verifica si hay conexión a través de Wi-Fi, datos móviles o Ethernet
        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            // Verifica si realmente se puede acceder a Internet (por ejemplo, haciendo una solicitud HTTP)
            return verificaAccesoInternet()
        }
    }
    return false
}
suspend fun verificaAccesoInternet(): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            // Verifica si se puede hacer una conexión a un servidor conocido (por ejemplo, google.com)
            val timeoutMs = 15000 // Tiempo de espera en milisegundos
            val socket = Socket()
            socket.connect(InetSocketAddress("google.com", 80), timeoutMs)
            socket.close()
            true // Hay acceso a Internet
        } catch (e: IOException) {
            false // No hay acceso a Internet
        }
    }
}
