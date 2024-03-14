package com.example.accesosicenet.data.baseDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "TablaUsuarioAccess")
data class TablaUsuarioAccess(
    @PrimaryKey @ColumnInfo(name = "matricula") val matricula: String = "",
    @ColumnInfo(name = "acceso") val acceso: Boolean = false,
    @ColumnInfo(name = "estatus") val estatus: String = "",
    @ColumnInfo(name = "tipoUsuario") val tipoUsuario: Int = 0,
    @ColumnInfo(name = "contrasenia") var contrasenia: String = "",
    @ColumnInfo(name = "fecha") var fecha: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
)