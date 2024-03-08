package com.example.accesosicenet.data.baseDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TablaUsuarioInfo")
data class TablaUsuarioInfo(
    @PrimaryKey @ColumnInfo(name = "matricula") val matricula: String,
    @ColumnInfo(name = "fechaReins") val fechaReins: String = "",
    @ColumnInfo(name = "modEducativo") val modEducativo: Int = 0,
    @ColumnInfo(name = "adeudo") val adeudo: Boolean = false,
    @ColumnInfo(name = "urlFoto") val urlFoto: String = "",
    @ColumnInfo(name = "adeudoDescripcion") val adeudoDescripcion: String = "",
    @ColumnInfo(name = "inscrito") val inscrito: Boolean = false,
    @ColumnInfo(name = "estatus") val estatus: String = "",
    @ColumnInfo(name = "semActual") val semActual: Int = 0,
    @ColumnInfo(name = "cdtosAcumulados") val cdtosAcumulados: Int = 0,
    @ColumnInfo(name = "cdtosActuales") val cdtosActuales: Int = 0,
    @ColumnInfo(name = "especialidad") val especialidad: String = "",
    @ColumnInfo(name = "carrera") val carrera: String = "",
    @ColumnInfo(name = "lineamiento") val lineamiento: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String = ""
)

