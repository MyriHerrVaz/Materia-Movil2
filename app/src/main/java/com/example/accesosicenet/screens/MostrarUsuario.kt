package com.example.accesosicenet.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import com.example.accesosicenet.viewModel.LoginView

@Composable
fun MostrarUsuario(viewModel: LoginView){
    Column {
        Text(
            text = "Alumno: "+ viewModel.usuario.nombre
        )
    }
}