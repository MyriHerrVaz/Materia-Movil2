package com.example.accesosicenet.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.accesosicenet.Navigation.AppScreens
import com.example.accesosicenet.R
import com.example.accesosicenet.viewModel.LoginView
import kotlinx.coroutines.launch

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
    val corrutina= rememberCoroutineScope()
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
                    if(viewmodel.getAccess(viewmodel.nocontrol,viewmodel.password)){
                        viewmodel.getInfo(viewmodel.nocontrol)
                        navController.navigate(route = AppScreens.Perfil.ruta)
                    }
                }
            }
        }) {
            Text(stringResource(R.string.boton))
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
