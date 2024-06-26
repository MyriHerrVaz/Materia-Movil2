package net.ivanvega.milocalizacionymapasb.ui.mapas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.maps.StreetViewPanoramaOptions
import com.google.android.gms.maps.StreetViewPanoramaView
import com.google.android.gms.maps.model.LatLng


@Composable
fun StreetView(navController: NavController) {
    val context = LocalContext.current
    val svpView = remember {
        StreetViewPanoramaView(context, StreetViewPanoramaOptions()
            .position(LatLng(20.17979791770884, -103.78421582134587)))
    }

    AndroidView(
        factory = { svpView },
        modifier = Modifier.fillMaxSize()
    ) { view ->
        view.onCreate(null)
        view.onResume()
        view.getStreetViewPanoramaAsync { panorama ->
            //Ubicacion inicial del Street View
            val initialPosition = LatLng(20.17979791770884, -103.78421582134587)
            panorama.setPosition(initialPosition)
        }
        }
}