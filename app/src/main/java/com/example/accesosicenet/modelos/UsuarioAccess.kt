package com.example.accesosicenet.modelos

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioAccess (
    val acceso: Boolean = false,
    val estatus: String = "",
    val tipoUsuario: Int = 0,
    val contrasenia: String="",
    val matricula: String=""
)