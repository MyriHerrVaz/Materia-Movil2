package com.example.accesosicenet.data.baseDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "TablaCardexR")
data class TablaCardexR(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "AvanceCdts") val AvanceCdts: Double = 0.0,
    @ColumnInfo(name = "CdtsAcum") val CdtsAcum: Int = 0,
    @ColumnInfo(name = "CdtsPlan") val CdtsPlan: Int = 0,
    @ColumnInfo(name = "MatAprobadas") val MatAprobadas: Int = 0,
    @ColumnInfo(name = "MatCursadas") val MatCursadas: Int = 0,
    @ColumnInfo(name = "PromedioGral") val PromedioGral: Double = 0.0,
    @ColumnInfo(name = "fecha") var fecha: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
)