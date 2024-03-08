package com.example.accesosicenet.data.baseDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tablafinales")
data class Tablafinales(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "Observaciones") var Observaciones: String = "",
    @ColumnInfo(name = "acred") var acred: String = "",
    @ColumnInfo(name = "calif") var calif: Int = 0,
    @ColumnInfo(name = "grupo") var grupo: String = "",
    @ColumnInfo(name = "materia") var materia: String = ""
)
