package net.ivanvega.milocalizacionymapasb.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.maps.android.compose.streetview.StreetView
import net.ivanvega.milocalizacionymapasb.ui.mapas.CrearMapas2
import net.ivanvega.milocalizacionymapasb.ui.mapas.MiPrimerMapa


@Composable
fun NavegacionApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "primerMapa") {
        composable(route = "primerMapa") {
            MiPrimerMapa(navController = navController)
        }
        composable(route = "dibujarMapa"){
            DibujarMapa(navController = navController)
        }
        composable(route = "streetView"){
            VistaCalle(navController = navController)
        }
        composable(route = "mapa2"){
            CrearMapas2(navController = navController)
        }
        composable(route = "pais"){
            RecomponerElementos(navController = navController)
        }
    }
}




