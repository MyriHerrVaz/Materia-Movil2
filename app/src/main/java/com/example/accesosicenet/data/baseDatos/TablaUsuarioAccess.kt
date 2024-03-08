package com.example.accesosicenet.data.baseDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TablaUsuarioAccess")
data class TablaUsuarioAccess(
    @PrimaryKey @ColumnInfo(name = "matricula") val matricula: String = "",
    @ColumnInfo(name = "acceso") val acceso: Boolean = false,
    @ColumnInfo(name = "estatus") val estatus: String = "",
    @ColumnInfo(name = "tipoUsuario") val tipoUsuario: Int = 0,
    @ColumnInfo(name = "contrasenia") val contrasenia: String = ""
)