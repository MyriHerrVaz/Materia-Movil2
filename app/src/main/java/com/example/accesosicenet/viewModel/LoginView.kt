package com.example.accesosicenet.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.accesosicenet.UsuarioApplication
import com.example.accesosicenet.data.usuarioRepository
import com.example.accesosicenet.modelos.UsuarioInfo

class LoginView(private val usuarioRepository: usuarioRepository): ViewModel(){
    var nocontrol by mutableStateOf("")
    var password by mutableStateOf("")
    var usuario by mutableStateOf(UsuarioInfo("",0,false,""
        ,"",false,"",0,0,
        0,"","",0,"",""))

    fun updateNocontrol(string: String){
        nocontrol=string
    }
    fun updatePassword(string: String){
        password=string
    }
    suspend fun getAccess(matricula: String, password: String): Boolean {
        return usuarioRepository.getAccess(matricula, password)
    }

    suspend fun getInfo(){
        usuario =usuarioRepository.getInfo()
        Log.d("Usuario", usuario.toString())
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