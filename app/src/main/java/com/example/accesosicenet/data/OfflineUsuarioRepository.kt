package com.example.accesosicenet.data

import com.example.accesosicenet.data.baseDatos.TablaCardex
import com.example.accesosicenet.data.baseDatos.TablaCardexR
import com.example.accesosicenet.data.baseDatos.TablaCargaAcademica
import com.example.accesosicenet.data.baseDatos.TablaUnidad
import com.example.accesosicenet.data.baseDatos.TablaUsuarioAccess
import com.example.accesosicenet.data.baseDatos.TablaUsuarioInfo
import com.example.accesosicenet.data.baseDatos.Tablafinales
import com.example.accesosicenet.data.interfaces.DaoUsuarioInfo
import kotlinx.coroutines.flow.Flow

interface usuarioRepositoryDB{
    suspend fun existeUsuario():Int
    suspend fun getUsuarioAccessDB(matricula: String, password: String):TablaUsuarioAccess
    suspend fun getUsuarioInfoDB():TablaUsuarioInfo
    suspend fun getUsuarioCardexDB():MutableList<TablaCardex>
    suspend fun getUsuarioCardexRDB():TablaCardexR
    suspend fun getUsuarioHorarioDB():MutableList<TablaCargaAcademica>
    suspend fun getUsuarioUnidadDB():MutableList<TablaUnidad>
    suspend fun getUsuarioFinalesDB():MutableList<Tablafinales>
    suspend  fun insertUsuarioAccess(UsuarioAccess: TablaUsuarioAccess)
    suspend  fun insertFinales(UsuarioFinal: Tablafinales)
    suspend  fun insertHorario(UsuarioHorario: TablaCargaAcademica)
    suspend  fun insertUsuarioInformacion(UsuarioInfo: TablaUsuarioInfo)
    suspend  fun insertCardex(Cardex: TablaCardex)
    suspend  fun insertUnidades(UsuarioUnidad: TablaUnidad)
    suspend  fun insertCardexR(CardexR: TablaCardexR)
    suspend fun deleteAccess()
    suspend fun deleteFinal()
    suspend fun deleteHorario()
    suspend fun deleteCardex()
    suspend fun deleteUnidades()
    suspend fun deleteCardexR()
    suspend fun deleteUsuarioInformacion()
}
class OfflineUsuarioRepository(private val DaoUsuario: DaoUsuarioInfo): usuarioRepositoryDB {

    override suspend fun existeUsuario():Int=DaoUsuario.existeUsuario()
    override suspend fun getUsuarioAccessDB(matricula: String, password: String):TablaUsuarioAccess = DaoUsuario.getUsuarioAccessDB(matricula,password)
    override suspend fun getUsuarioInfoDB():TablaUsuarioInfo = DaoUsuario.getUsuarioInfoDB()
    override suspend fun getUsuarioCardexDB():MutableList<TablaCardex> = DaoUsuario.getUsuarioCardexDB()
    override suspend fun getUsuarioCardexRDB():TablaCardexR = DaoUsuario.getCardexR()
    override suspend fun getUsuarioHorarioDB():MutableList<TablaCargaAcademica> = DaoUsuario.getUsuarioHorarioDB()
    override suspend fun getUsuarioUnidadDB():MutableList<TablaUnidad> =DaoUsuario.getUsuarioUnidadDB()
    override suspend fun getUsuarioFinalesDB():MutableList<Tablafinales> = DaoUsuario.getUsuarioFinalesDB()
    override suspend  fun insertUsuarioAccess(UsuarioAccess: TablaUsuarioAccess) = DaoUsuario.insertUsuarioAccess(UsuarioAccess)
    override suspend  fun insertFinales(UsuarioFinal: Tablafinales) = DaoUsuario.insertFinales(UsuarioFinal)
    override suspend  fun insertHorario(UsuarioHorario: TablaCargaAcademica) = DaoUsuario.insertHorario(UsuarioHorario)
    override suspend  fun insertUsuarioInformacion(UsuarioInfo: TablaUsuarioInfo) =DaoUsuario.insertUsuarioInformacion(UsuarioInfo)
    override suspend  fun insertCardex(Cardex: TablaCardex) = DaoUsuario.insertCardex(Cardex)
    override suspend  fun insertUnidades(UsuarioUnidad: TablaUnidad) = DaoUsuario.insertUnidades(UsuarioUnidad)
    override suspend  fun insertCardexR(CardexR: TablaCardexR) = DaoUsuario.insertCardexR(CardexR)
    override suspend fun deleteAccess() = DaoUsuario.deleteAccess()
    override suspend fun deleteFinal() = DaoUsuario.deleteFinal()
    override suspend fun deleteHorario() = DaoUsuario.deleteHorario()
    override suspend fun deleteCardex() = DaoUsuario.deleteCardex()
    override suspend fun deleteUnidades() = DaoUsuario.deleteUnidades()
    override suspend fun deleteCardexR() = DaoUsuario.deleteCardexR()
    override suspend fun deleteUsuarioInformacion() = DaoUsuario.deleteUsuarioInformacion()
}