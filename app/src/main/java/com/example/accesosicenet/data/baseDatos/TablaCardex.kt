package com.example.accesosicenet.data.baseDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "TablaCardex")
data class TablaCardex(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "A1") var A1: String? = "",
    @ColumnInfo(name = "A2") var A2: String? = "",
    @ColumnInfo(name = "A3") var A3: String? = "",
    @ColumnInfo(name = "Acred") var Acred: String = "",
    @ColumnInfo(name = "Calif") var Calif: Int = 0,
    @ColumnInfo(name = "Cdts") var Cdts: Int = 0,
    @ColumnInfo(name = "ClvMat") var ClvMat: String = "",
    @ColumnInfo(name = "ClvOfiMat") var ClvOfiMat: String = "",
    @ColumnInfo(name = "Materia") var Materia: String = "",
    @ColumnInfo(name = "P1") var P1: String? = "",
    @ColumnInfo(name = "P2") var P2: String? = "",
    @ColumnInfo(name = "P3") var P3: String? = "",
    @ColumnInfo(name = "S1") var S1: String? = "",
    @ColumnInfo(name = "S2") var S2: String? = "",
    @ColumnInfo(name = "S3") var S3: String? = "",
    @ColumnInfo(name = "fecha") var fecha: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
)
