package com.example.accesosicenet.data.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.accesosicenet.data.baseDatos.TablaCardex
import com.example.accesosicenet.data.baseDatos.TablaCardexR
import com.example.accesosicenet.data.baseDatos.TablaCargaAcademica
import com.example.accesosicenet.data.baseDatos.TablaUnidad
import com.example.accesosicenet.data.baseDatos.TablaUsuarioAccess
import com.example.accesosicenet.data.baseDatos.TablaUsuarioInfo
import com.example.accesosicenet.data.baseDatos.Tablafinales
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoUsuarioInfo {
    @Insert
    suspend fun insertUsuarioAccess(UsuarioAccess: TablaUsuarioAccess)
    @Insert
    suspend fun insertFinales(UsuarioFinal: Tablafinales)
    @Insert
    suspend fun insertHorario(UsuarioHorario: TablaCargaAcademica)
    @Insert
    suspend fun insertUsuarioInformacion(UsuarioInfo: TablaUsuarioInfo)
    @Insert
    suspend fun insertCardex(Cardex: TablaCardex)
    @Insert
    suspend fun insertUnidades(UsuarioUnidad: TablaUnidad)
    @Insert
    suspend fun insertCardexR(CardexR: TablaCardexR)

    //--------------Obtenger---------------------------------
    @Query("SELECT * from TablaUsuarioAccess where (matricula=:matricula and contrasenia=:password)")
    suspend fun getUsuarioAccessDB(matricula: String, password: String):TablaUsuarioAccess
    @Query("SELECT COUNT(*) from TablaUsuarioAccess")
    suspend fun existeUsuario():Int

    @Query("SELECT * from Tablafinales")
    suspend fun getUsuarioFinalesDB():List<Tablafinales>

    @Query("SELECT * from TablaCargaAcademica")
    suspend fun getUsuarioHorarioDB():List<TablaCargaAcademica>

    @Query("SELECT * from TablaUsuarioInfo")
    suspend fun getUsuarioInfoDB():TablaUsuarioInfo

    @Query("SELECT * from TablaCardex")
    suspend fun getUsuarioCardexDB(lineamiento: String):List<TablaCardex>

    @Query("SELECT * from TablaUnidad")
    suspend fun getUsuarioUnidadDB():List<TablaUnidad>

    @Query("SELECT * from TablaCardexR")
    suspend fun getCardexR(): TablaCardexR

    //--------------Delete---------------------------------
    @Query("delete from TablaUsuarioAccess")
    suspend fun deleteAccess()

    @Query("delete from Tablafinales")
    suspend fun deleteFinal()

    @Query("delete from TablaCargaAcademica")
    suspend fun deleteHorario()

    @Query("delete from TablaUsuarioInfo")
    suspend fun deleteUsuarioInformacion()

    @Query("delete from TablaCardex")
    suspend fun deleteCardex()

    @Query("delete from TablaUnidad")
    suspend fun deleteUnidades()

    @Query("delete from TablaCardexR")
    suspend fun deleteCardexR()
}