package net.ivanvega.milocalizacionymapasb.ui.mapas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun RecoposingeElements(navController: NavController){
    val singapore = LatLng(20.104196945984338, -101.39476896795712) // Latitud y longitud
    val markerState = rememberMarkerState(position = singapore)


    LaunchedEffect(Unit) {
        repeat(10) {
            delay(5.seconds)
            val old = markerState.position
            markerState.position = LatLng(old.latitude + 1.0, old.longitude + 2.0)
        }
    }
}