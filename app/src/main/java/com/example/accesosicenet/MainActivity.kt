package com.example.accesosicenet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.accesosicenet.screens.MostrarUsuario
import com.example.accesosicenet.ui.theme.AccesoSicenetTheme
import com.example.accesosicenet.viewModel.LoginView
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccesoSicenetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navaControl = rememberNavController()
                    val viewmodel:LoginView =  viewModel(factory = LoginView.Factory)
                    SimpleOutlinedTextFieldSample(viewmodel, navaControl)

                    NavHost(
                        navController = navaControl,
                        startDestination = "login",
                    ){
                        composable(route ="login"){
                            SimpleOutlinedTextFieldSample(viewmodel,navaControl)
                        }
                        composable(route ="MostrarUsuario"){
                            MostrarUsuario(viewModel = viewmodel)
                        }

                    }
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
fun PassField(viewmodel: LoginView,modifier: Modifier = Modifier) {
    TextField(
        value = viewmodel.password,
        onValueChange = { viewmodel.password = it},
        singleLine = true,
        label = { Text(stringResource(R.string.pass)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = modifier
    )
}

@Composable
fun SimpleOutlinedTextFieldSample(
    viewmodel: LoginView,
    navaControl: NavHostController
) {
    val corrutina= rememberCoroutineScope()
    Column {
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
                        viewmodel.getInfo()
                        navaControl.navigate("MostrarUsuario")
                    }
                }
            }
        }) {
            Text(stringResource(R.string.boton))
        }
    }
}