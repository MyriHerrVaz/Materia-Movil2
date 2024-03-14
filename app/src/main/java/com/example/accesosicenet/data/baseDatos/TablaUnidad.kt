package com.example.accesosicenet.data.baseDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Entity(tableName = "TablaUnidad")
data class TablaUnidad(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "C1") var C1: String = "",
    @ColumnInfo(name = "C10") var C10: String = "",
    @ColumnInfo(name = "C11") var C11: String = "",
    @ColumnInfo(name = "C12") var C12: String = "",
    @ColumnInfo(name = "C13") var C13: String = "",
    @ColumnInfo(name = "C2") var C2: String = "",
    @ColumnInfo(name = "C3") var C3: String = "",
    @ColumnInfo(name = "C4") var C4: String = "",
    @ColumnInfo(name = "C5") var C5: String = "",
    @ColumnInfo(name = "C6") var C6: String = "",
    @ColumnInfo(name = "C7") var C7: String = "",
    @ColumnInfo(name = "C8") var C8: String = "",
    @ColumnInfo(name = "C9") var C9: String = "",
    @ColumnInfo(name = "Grupo") var Grupo: String = "",
    @ColumnInfo(name = "Materia") var Materia: String = "",
    @ColumnInfo(name = "Observaciones") var Observaciones: String = "",
    @ColumnInfo(name = "UnidadesActivas") var UnidadesActivas: String = "",
    @ColumnInfo(name = "fecha") var fecha: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
)