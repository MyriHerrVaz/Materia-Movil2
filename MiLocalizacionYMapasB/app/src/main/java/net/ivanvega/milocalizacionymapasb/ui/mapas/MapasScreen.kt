package net.ivanvega.milocalizacionymapasb.ui.mapas

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PinConfig
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun MiPrimerMapa() {
    val singapore = LatLng(20.13940326357506, -101.15073142883558)
    val markerState = rememberMarkerState(position = singapore)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    // Set properties using MapProperties which you can use to recompose the map
    var mapProperties by remember {
        mutableStateOf(
            MapProperties(maxZoomPreference = 10f, minZoomPreference = 5f)
        )
    }
    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(mapToolbarEnabled = false)
        )
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(cameraPositionState = cameraPositionState)
        LaunchedEffect(Unit) {
            repeat(10) {
                delay(5.seconds)
                val old = markerState.position
                markerState.position = LatLng(old.latitude + 1.0, old.longitude + 2.0)
            }
        }
        Column {
            Button(onClick = {
                // Move the camera to a new zoom level
                cameraPositionState.move(CameraUpdateFactory.zoomIn())
            }) {
                Text(text = "Zoom In")
            }
        }
        GoogleMap(
            googleMapOptionsFactory = {
                GoogleMapOptions().mapId("Itsur")
            }
        ){
            val pinConfig = PinConfig.builder()
                .setBackgroundColor(Color.MAGENTA)
                .build()

            // Agregar MarkerInfoWindowContent y MarkerInfoWindow aquí
            MarkerInfoWindowContent(
                state = MarkerState(position = LatLng(19.702443183103323, -101.19232923360192))
            ){ marker ->
                // Contenido de la ventana de información del marcador
                Text(
                    text = marker.title ?: "Catedral de Morelia"
                )
            }
            MarkerInfoWindow(state = MarkerState(position = LatLng(19.782144034817463, -100.65724525970312)),
                title = "Los Azufres michoacan"
            ){ marker ->
                // Estilo y diseño de la ventana de información del marcador
                Column {
                    Text(
                        text = marker.snippet ?: "Lugar relajante"
                    )
                }
            }
        }
    }


}