package com.example.accesosicenet.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.accesosicenet.UsuarioApplication
import com.example.accesosicenet.data.usuarioRepository

class LoginView(private val usuarioRepository: usuarioRepository): ViewModel(){
    var nocontrol by mutableStateOf("")
    var password by mutableStateOf("")

    fun updateNocontrol(string: String){
        nocontrol=string
    }
    fun updatePassword(string: String){
        password=string
    }
    suspend fun getAccess(matricula: String, password: String): Boolean {
        return usuarioRepository.getAccess(matricula, password)
    }

    suspend fun getInfo():String{
        return usuarioRepository.getInfo()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UsuarioApplication)
                val usuarioApplication = application.container.usuariosRepository
                LoginView(usuarioRepository = usuarioApplication)
            }
        }
    }
}