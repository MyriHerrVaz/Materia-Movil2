package com.example.accesosicenet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    SimpleOutlinedTextFieldSample("Usuario")
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    AccesoSicenetTheme {
        SimpleOutlinedTextFieldSample("síii")
    }
}

@Composable
fun SimpleOutlinedTextFieldSample(name: String) {
    var text by remember { mutableStateOf("") }

    Column {
        Text(
            text = "Hello $name!",
            modifier = Modifier
        )
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("$name") },
            modifier = Modifier
        )
        Text(
            text = "Hello $name!",
            modifier = Modifier
        )
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("$name") },
            modifier = Modifier
        )
    }
}