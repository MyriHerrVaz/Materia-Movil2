package com.example.accesosicenet.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.accesosicenet.UsuarioApplication
import com.example.accesosicenet.data.OfflineUsuarioRepository
import com.example.accesosicenet.data.usuarioRepository
import com.example.accesosicenet.data.usuarioRepositoryDB
import com.example.accesosicenet.modelos.CalifFinales
import com.example.accesosicenet.modelos.CalifUnidad
import com.example.accesosicenet.modelos.Cardex
import com.example.accesosicenet.modelos.CardexR
import com.example.accesosicenet.modelos.CargaAcademica
import com.example.accesosicenet.modelos.UsuarioAccess
import com.example.accesosicenet.modelos.UsuarioInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginView(private val usuarioRepository: usuarioRepository,
                private val usuarioRepositoryBD: usuarioRepositoryDB): ViewModel(){
    var nocontrol by mutableStateOf("")
    var password by mutableStateOf("")
    var UsuarioA by mutableStateOf(UsuarioAccess())
    var usuario by mutableStateOf(UsuarioInfo())
    var cardex: MutableList<Cardex> = mutableListOf()
    var cardexR by mutableStateOf(CardexR())
    var horario by mutableStateOf(emptyList<CargaAcademica>())
    var unidad by mutableStateOf(emptyList<CalifUnidad>())
    var final by mutableStateOf(emptyList<CalifFinales>())

    fun updateNocontrol(string: String){
        nocontrol=string
    }
    fun updatePassword(string: String){
        password=string
    }
    suspend fun getAccess(matricula: String, password: String):Boolean {
         UsuarioA= Gson().fromJson(usuarioRepository.getAccess(matricula, password), UsuarioAccess::class.java)
        return UsuarioA.acceso
    }

    suspend fun getInfo(matricula: String){
        usuario = Gson().fromJson(usuarioRepository.getInfo(matricula), UsuarioInfo::class.java)
        Log.d("Usuario", usuario.toString())
    }

    //Cardex
    suspend fun getCardex(lineamiento:String?){
        viewModelScope.launch {
            val card = viewModelScope.async {
                usuarioRepository.getCardex(
                    lineamiento?.let {
                        it
                    } ?: "")
            }
            var materia=card.await().split("{","},{")
            for (i in 2..materia.size-3){
                val mat= Gson().fromJson("{"+materia[i]+"}", Cardex::class.java)
                cardex.add(mat)
            }
            var m52=materia[52].split("}").get(0)
            cardex.add(Gson().fromJson("{"+m52+"}", Cardex::class.java))
            var resumenC=materia[53].split("}}").get(0)
            cardexR=Gson().fromJson("{"+resumenC+"}", CardexR::class.java)
        }
    }

    //Carga Academica
    suspend fun getCargaAcademica(){
        horario=Gson().fromJson(usuarioRepository.getCargaAcademica(), object : TypeToken<List<CargaAcademica>>(){}.type)
        Log.d("Horario", horario.toString())
    }

    //Calificacion de unidades
    suspend fun getCalificacionUnidad(){
        unidad=Gson().fromJson(usuarioRepository.getCargaAcademica(), object : TypeToken<List<CalifUnidad>>(){}.type)
        Log.d("Unidad", unidad.toString())
    }
    //calificaciones finales
    suspend fun getCalificacionFinal(modEducativo:String?){
        final=Gson().fromJson(usuarioRepository.getCargaAcademica(), object : TypeToken<List<CalifFinales>>(){}.type)
        Log.d("Final", final.toString())
    }

    suspend fun saveUsuarioInfo() {
        usuarioRepositoryBD.insertUsuarioInformacion()
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UsuarioApplication)
                val usuarioApplication = application.container.usuariosRepository
                val usuarioApplicationBD = application.container.usuariosRepositoryBD
                LoginView(usuarioRepository = usuarioApplication,
                    usuarioRepositoryBD = usuarioApplicationBD)
            }
        }
    }
}