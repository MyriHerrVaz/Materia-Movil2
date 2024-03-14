package com.example.accesosicenet.data.baseDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "Tablafinales")
data class Tablafinales(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "Observaciones") var Observaciones: String = "",
    @ColumnInfo(name = "acred") var acred: String = "",
    @ColumnInfo(name = "calif") var calif: Int = 0,
    @ColumnInfo(name = "grupo") var grupo: String = "",
    @ColumnInfo(name = "materia") var materia: String = "",
    @ColumnInfo(name = "fecha") var fecha: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
)
