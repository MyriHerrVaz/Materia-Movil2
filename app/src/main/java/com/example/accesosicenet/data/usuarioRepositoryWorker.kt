package com.example.accesosicenet.data

import androidx.work.WorkInfo
import kotlinx.coroutines.flow.Flow

interface usuarioRepositoryWorker {
    val WorkUsuarioInfo: Flow<WorkInfo>
    val WorkFinales: Flow<WorkInfo>
    val WorkUnidades: Flow<WorkInfo>
    val WorkCargaCademica: Flow<WorkInfo>
    val WorkCardex: Flow<WorkInfo>
    val WorkCardex2: Flow<WorkInfo>
    val WorkCardexR: Flow<WorkInfo>
    fun getAccess(matricula: String, password: String)
    fun getCargaAcademica()
    fun getCardex(lineamiento: String)
    fun getCardex2(lineamiento:String)
    fun getCardexR(lineamiento:String)
    fun getCalificacionUnidad()
    fun getCalificacionFinal(modoEducativo:String)
}