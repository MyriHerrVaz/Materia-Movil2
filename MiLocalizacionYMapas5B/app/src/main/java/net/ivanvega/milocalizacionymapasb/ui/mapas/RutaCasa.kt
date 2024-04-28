package net.ivanvega.milocalizacionymapasb.ui.mapas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import net.ivanvega.milocalizacionymapasb.Manifest
import net.ivanvega.milocalizacionymapasb.ui.location.CurrentLocationContent
import net.ivanvega.milocalizacionymapasb.ui.location.PermissionBox

//PERMISOS
@SupressLint("MissingPermission")
@Composable
fun GoogleMapScreen(){
    val permissions : listOf(
    Manifest.permission.ACCESS_COARSE_LOCALITATION,
        Manifest.permission.ACCESS_FINE_LOCALITATION,
    )
    PermissionBox(
        permissions = permissions,
        requiredPermissions = listOf(permissions.first()),
        onGranted = {
            CurrentLocationContent(
                usePreciseLocation = it.contains(android.Manifest.permission.ACCESS_FINE_LOCATION)
            )
        },
    )
}

//PERMISOS
@RequieresPermission(
anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)

//RAMA PRINCIPAL PARA LAS UBICACIONES Y BOTONES
@Composable
fun CurrentLocationContent(usePermissionLocation: Boolean){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val locationServices = remember {
        LocaltionServices.getFusedLocationProviderClient(context)
    }
    var CasaMyriam = remember {
        LatLng(20.098503652706885, -101.39249902675638)
    }
    var CasaSantiago = remember {
        LatLng(20.17900163991653, -101.43490493353579)
    }
    var Itsur = remember {
        LatLng(20.1404425247015, -101.15054421966045)
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(Itsur, 10f)
    }
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        val markerState = rememberMarkerState(position = CasaMyriam)
        val markerState2 = rememberMarkerState(position = CasaSantiago)
        Box(Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                // point Casa
                Marker(
                    state = MarkerState(position = CasaMyriam),
                    title = "Casa de Myriam",
                    snippet = "Casa"
                )
                // point Casa
                Marker(
                    state = MarkerState(position = CasaSantiago),
                    title = "Casa de Jesse",
                    snippet = "Casa"
                )

    }
