package com.example.accesosicenet.data.baseDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TablaCargaAcademica")
data class TablaCargaAcademica(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "CreditosMateria") var CreditosMateria: String = "",
    @ColumnInfo(name = "Docente") var Docente: String = "",
    @ColumnInfo(name = "EstadoMateria") var EstadoMateria: String = "",
    @ColumnInfo(name = "Grupo") var Grupo: String = "",
    @ColumnInfo(name = "Jueves") var Jueves: String = "",
    @ColumnInfo(name = "Lunes") var Lunes: String = "",
    @ColumnInfo(name = "Martes") var Martes: String = "",
    @ColumnInfo(name = "Materia") var Materia: String = "",
    @ColumnInfo(name = "Miercoles") var Miercoles: String = "",
    @ColumnInfo(name = "Observaciones") var Observaciones: String = "",
    @ColumnInfo(name = "Sabado") var Sabado: String = "",
    @ColumnInfo(name = "Semipresencial") var Semipresencial: String = "",
    @ColumnInfo(name = "Viernes") var Viernes: String = "",
    @ColumnInfo(name = "clvOficial") var clvOficial: String = ""
)
