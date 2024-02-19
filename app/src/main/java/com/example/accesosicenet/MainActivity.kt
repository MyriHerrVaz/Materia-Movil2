package com.example.accesosicenet

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
                    SimpleOutlinedTextFieldSample()
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    AccesoSicenetTheme {
        SimpleOutlinedTextFieldSample()
    }
}
@Composable
fun NameField(modifier: Modifier = Modifier) {
    var amountInput by remember {mutableStateOf("")}
    TextField(
        value = amountInput,
        onValueChange = { amountInput = it},
        singleLine = true,
        label = { Text(stringResource(R.string.usuario)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = modifier
    )
}
@Composable
fun PassField(modifier: Modifier = Modifier) {
    var amountInput by remember {mutableStateOf("")}
    TextField(
        value = amountInput,
        onValueChange = { amountInput = it},
        singleLine = true,
        label = { Text(stringResource(R.string.pass)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = modifier
    )
}

@Composable
fun SimpleOutlinedTextFieldSample(
    viewmodel: LoginView = viewModel(factory = LoginView.Factory)
) {
    val corrutina= rememberCoroutineScope()
    Column {
        Text(
            text = "Escribe el usuario!",
            modifier = Modifier
        )
        NameField(modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth()
        )
        Text(
            text = "Contraseña:",
            modifier = Modifier
        )
        PassField(modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth()
        )
        Button(onClick = {
            if (!viewmodel.nocontrol.equals("") && !viewmodel.password.equals("")){
                corrutina.launch {
                    if(viewmodel.getAccess(viewmodel.nocontrol,viewmodel.password)){
                        var info=viewmodel.getInfo()
                        Log.d("info",info)
                    }
                }
            }
        }) {
            Text(stringResource(R.string.boton))
        }
    }
}