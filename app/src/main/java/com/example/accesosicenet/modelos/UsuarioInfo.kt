package com.example.accesosicenet.modelos

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioInfo(
    val fechaReins: String ="",
    val modEducativo:Int=0,
    val adeudo: Boolean=false,
    val urlFoto: String="",
    val adeudoDescripcion: String="",
    val inscrito:Boolean=false,
    val estatus: String="",
    val semActual: Int=0,
    val cdtosAcumulados: Int=0,
    val cdtosActuales: Int=0,
    val especialidad: String="",
    val carrera:String="",
    val lineamiento: Int=0,
    val nombre: String="",
    val matricula: String=""
)

