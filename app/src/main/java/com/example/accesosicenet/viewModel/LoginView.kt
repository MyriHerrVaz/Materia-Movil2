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
import androidx.work.WorkInfo
import com.example.accesosicenet.UsuarioApplication
import com.example.accesosicenet.data.baseDatos.TablaCardex
import com.example.accesosicenet.data.baseDatos.TablaCardexR
import com.example.accesosicenet.data.baseDatos.TablaCargaAcademica
import com.example.accesosicenet.data.baseDatos.TablaUnidad
import com.example.accesosicenet.data.baseDatos.TablaUsuarioAccess
import com.example.accesosicenet.data.baseDatos.TablaUsuarioInfo
import com.example.accesosicenet.data.baseDatos.Tablafinales
import com.example.accesosicenet.data.usuarioRepository
import com.example.accesosicenet.data.usuarioRepositoryDB
import com.example.accesosicenet.data.usuarioRepositoryWorker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class LoginView(private val usuarioRepository: usuarioRepository,
                private val usuarioRepositoryBD: usuarioRepositoryDB,
                private val usuarioRepositoryWorker: usuarioRepositoryWorker
    ): ViewModel(){
    var Internert by mutableStateOf(false)
    var presionarCarga by mutableStateOf(false)
    var presionarFinal by mutableStateOf(false)
    var presionarUnidad by mutableStateOf(false)
    var presionarCardex by mutableStateOf(false)
    var presionarCardexR by mutableStateOf(false)
    var Cardex1Fin by mutableStateOf(false)
    var Cardex1Act by mutableStateOf(false)
    var Cardex2Fin by mutableStateOf(false)
    var Cardex2Act by mutableStateOf(false)
    var nocontrol by mutableStateOf("")
    var password by mutableStateOf("")
    var UsuarioAccess:TablaUsuarioAccess = TablaUsuarioAccess()
    var UsuarioInfo:TablaUsuarioInfo = TablaUsuarioInfo()
    var cardex: MutableList<TablaCardex> = mutableListOf()
    var cardexR by mutableStateOf(TablaCardexR())
    var CargaAcademica: MutableList<TablaCargaAcademica> = mutableListOf()
    var unidad: MutableList<TablaUnidad> = mutableListOf()
    var final:MutableList<Tablafinales> = mutableListOf()

    fun updateNocontrol(string: String){
        nocontrol=string
    }
    fun updatePassword(string: String){
        password=string
    }
    val workerUiStateAccess: StateFlow<WorkerAccessState> = usuarioRepositoryWorker.WorkUsuarioInfo
        .map { info ->
            val acceso = info.outputData.getString("Access")
            val informacion=info.outputData.getString("Info")
            when {
                info.state.isFinished  && !acceso.isNullOrEmpty() && !informacion.isNullOrEmpty()-> {
                    WorkerAccessState.Complete(acceso, informacion)
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000,0),
            initialValue = WorkerAccessState.Default
        )

    val workerUiStateFinales: StateFlow<WorkerAccessState> = usuarioRepositoryWorker.WorkFinales
        .map { info ->
            val finales = info.outputData.getString("finales")
            when {
                info.state.isFinished  && !finales.isNullOrEmpty()-> {
                    WorkerAccessState.Complete(finales,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000,0),
            initialValue = WorkerAccessState.Default
        )
    val workerUiStateUnidades: StateFlow<WorkerAccessState> = usuarioRepositoryWorker.WorkUnidades
        .map { info ->
            val unidades = info.outputData.getString("Unidades")
            when {
                info.state.isFinished  && !unidades.isNullOrEmpty()-> {
                    WorkerAccessState.Complete(unidades,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000,0),
            initialValue = WorkerAccessState.Default
        )

    val workerUiStateCargaAcademica: StateFlow<WorkerAccessState> = usuarioRepositoryWorker.WorkCargaCademica
        .map { info ->
            val carga = info.outputData.getString("CargaAcademica")
            when {
                info.state.isFinished  && !carga.isNullOrEmpty()-> {
                    WorkerAccessState.Complete(carga,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000,0),
            initialValue = WorkerAccessState.Default
        )

    val workerUiStateCardex: StateFlow<WorkerAccessState> = usuarioRepositoryWorker.WorkCardex
        .map { info ->
            val cardex = info.outputData.getString("Cardexparte1")
            when {
                info.state.isFinished  && !cardex.isNullOrEmpty()-> {
                    Cardex1Fin=true
                    WorkerAccessState.Complete(cardex,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000,0),
            initialValue = WorkerAccessState.Default
        )

    val workerUiStateCardex2: StateFlow<WorkerAccessState> = usuarioRepositoryWorker.WorkCardex2
        .map { info ->
            val cardex2 = info.outputData.getString("Cardexparte2")
            when {
                info.state.isFinished  && !cardex2.isNullOrEmpty()-> {
                    Cardex2Fin=true
                    WorkerAccessState.Complete(cardex2,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000,0),
            initialValue = WorkerAccessState.Default
        )
    val workerUiStateCardexR: StateFlow<WorkerAccessState> = usuarioRepositoryWorker.WorkCardexR
        .map { info ->
            val cardexR = info.outputData.getString("CardexResumen")
            when {
                info.state.isFinished  && !cardexR.isNullOrEmpty()-> {
                    WorkerAccessState.Complete(cardexR,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000,0),
            initialValue = WorkerAccessState.Default
        )

    fun getAccess() {
        usuarioRepositoryWorker.getAccess(nocontrol, password)
    }
    //Cardex
    fun getCardex(){
        usuarioRepositoryWorker.getCardex(UsuarioInfo.lineamiento.toString())
        usuarioRepositoryWorker.getCardex2(UsuarioInfo.lineamiento.toString())
        usuarioRepositoryWorker.getCardexR(UsuarioInfo.lineamiento.toString())
    }

    //Carga Academica
    fun getCargaAcademica(){
        usuarioRepositoryWorker.getCargaAcademica()
    }

    //Calificacion de unidades
    fun getCalificacionUnidad(){
        usuarioRepositoryWorker.getCalificacionUnidad()
    }
    //calificaciones finales
    fun getCalificacionFinal(){
        usuarioRepositoryWorker.getCalificacionFinal(UsuarioInfo.modEducativo.toString())
    }

    fun AccesoApp(jsonUsuario:String,jsonUsuarioInfo:String):Boolean{
        var acceso=Gson().fromJson(jsonUsuario, TablaUsuarioAccess::class.java)
        var info=Gson().fromJson(jsonUsuarioInfo, TablaUsuarioInfo::class.java)
        Log.d("worker",jsonUsuario)
        Log.d("worker",jsonUsuarioInfo)
        if(acceso.acceso ==true){
            UsuarioAccess=acceso
            UsuarioInfo=info
            return true
        }
        return false
    }
    fun mostrarFinales(json:String){
        val type = object : TypeToken<List<Tablafinales>>() {}.type
        final= Gson().fromJson(json, type)
    }

    fun mostrarCargaAcademica(json:String){
        val type = object : TypeToken<List<TablaCargaAcademica>>() {}.type
        CargaAcademica= Gson().fromJson(json, type)
    }

    fun mostrarUnidades(json:String){
        val type = object : TypeToken<List<TablaUnidad>>() {}.type
        var listaUnidades:MutableList<TablaUnidad> = Gson().fromJson(json, type)
        unidad = listaUnidades
    }
    fun mostrarCardexR(jsonCardexR:String){
        cardexR= Gson().fromJson(jsonCardexR, TablaCardexR::class.java)
    }

    fun mostrarCardex1(jsonCardex:String){
        var type = object : TypeToken<List<TablaCardex>>() {}.type
        var CardexL: MutableList<TablaCardex> = Gson().fromJson(jsonCardex, type)
        CardexL.forEach {
            cardex.add(it)
        }
        Cardex1Act=true
    }

    fun mostrarCardex2(jsonCardex:String){
        var type = object : TypeToken<List<TablaCardex>>() {}.type
        var CardexL: MutableList<TablaCardex> = Gson().fromJson(jsonCardex, type)
        CardexL.forEach {
            cardex.add(it)
        }
        Cardex2Act=true
    }
    fun limpiarKardex(){
        cardex.clear()
    }

    suspend fun getAccessDB():Boolean{
        var usuario=usuarioRepositoryBD.getUsuarioAccessDB(nocontrol.uppercase(),password)
        usuario?.let {
                if (usuario.contrasenia==password && usuario.tipoUsuario==0){
                    UsuarioInfo=usuarioRepositoryBD.getUsuarioInfoDB()
                    return true
                }
                return false
        }
        return false
    }

    suspend fun getFinalesDB(){
        var finalesDatos=usuarioRepositoryBD.getUsuarioFinalesDB()
        if(!finalesDatos.isNullOrEmpty()) {
            final = finalesDatos
        }
    }

    suspend fun getUnidadesDB(){
        var parcialesDatos=usuarioRepositoryBD.getUsuarioUnidadDB()
        if(!parcialesDatos.isNullOrEmpty()) {
            unidad = parcialesDatos
        }
    }

    suspend fun getCargaAcademicaDB(){
        var horarioDatos=usuarioRepositoryBD.getUsuarioHorarioDB()
        if(!horarioDatos.isNullOrEmpty()) {
            CargaAcademica = horarioDatos
        }
    }

    suspend fun getCardexDB(){
        var CardexCompleto=usuarioRepositoryBD.getUsuarioCardexDB()
        var CardexR=usuarioRepositoryBD.getUsuarioCardexRDB()
        CardexR?.let {
            cardex= CardexCompleto!!
            cardexR=CardexR
        }

    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UsuarioApplication)
                val usuarioApplication = application.container.usuariosRepository
                val usuarioApplicationBD = application.container.usuariosRepositoryBD
                val usuarioApplicationWorker = application.container.usuariosRepositoryWorker
                LoginView(usuarioRepository = usuarioApplication,
                    usuarioRepositoryBD = usuarioApplicationBD,
                    usuarioApplicationWorker)
            }
        }
    }
}

sealed interface WorkerAccessState {
    object Default : WorkerAccessState
    object Loading : WorkerAccessState
    data class Complete(val outputUno: String,val outputDos: String) : WorkerAccessState
}