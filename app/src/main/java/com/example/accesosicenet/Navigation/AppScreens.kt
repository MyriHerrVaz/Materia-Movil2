package com.example.accesosicenet.Navigation

sealed class AppScreens(val ruta: String) {
    object Login: AppScreens("Login_screen")
    object Perfil: AppScreens("Perfil_screen")
    object CalificacionFinal: AppScreens("CalificacionFinal_screen")
    object CalificacionUnidad: AppScreens("CalificacionUnidad_screen")
    object Cardex: AppScreens("Cardex_screen")
    object CardexA: AppScreens("CardexA_screen")
    object Horario: AppScreens("Horario_screen")

}