package com.example.accesosicenet

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.accesosicenet.ui.theme.AccesoSicenetTheme

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
                    SimpleOutlinedTextFieldSample("Usuario","Contraseña")
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    AccesoSicenetTheme {
        SimpleOutlinedTextFieldSample("usuario", "contraseña")
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
fun SimpleOutlinedTextFieldSample(usuario: String, pass: String) {
    var text by remember { mutableStateOf("") }

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
        Button(onClick = { /*TODO*/ }) {
            Text(stringResource(R.string.boton))
        }
    }
}