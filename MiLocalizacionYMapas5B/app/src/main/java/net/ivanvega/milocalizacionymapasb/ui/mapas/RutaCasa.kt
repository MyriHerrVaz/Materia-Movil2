package net.ivanvega.milocalizacionymapasb.ui.mapas

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import net.ivanvega.milocalizacionymapasb.Manifest
import net.ivanvega.milocalizacionymapasb.ui.location.CurrentLocationContent
import net.ivanvega.milocalizacionymapasb.ui.location.PermissionBox
import retrofit2.Retrofit

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
                //verifica la posicion actual con la casa de juancarlos
                if (!markerState.position.equals(CasaMyriam)) {
                    Marker(
                        state = MarkerState(position = markerState.position),
                        title = "Ubicacion actual"
                    )
                    val RouteList = remember { mutableStateOf<List<LatLng>>(emptyList()) }
                    createRoute(CasaMyriam markerState.position) { routePoints ->
                        val pointsList = mutableListOf<LatLng>()
                        for (i in routePoints.indices step 2) {
                            val lat = routePoints[i]
                            val lng = routePoints[i + 1]
                            pointsList.add(LatLng(lat, lng))
                        }
                        RouteList.value = pointsList
                    }
                    // Se pintara la linea para la casa
                    Polyline(points = RouteList.value)

                    //lo mismo pero ahora con la casa de JESSE
                } else if (!markerState2.position.equals(CasaSantiago)) {
                    Marker(
                        state = MarkerState(position = markerState2.position),
                        title = "Ubicacion actual"
                    )
                    val RouteList = remember { mutableStateOf<List<LatLng>>(emptyList()) }
                    createRoute(CasaSantiago, markerState2.position) { routePoints ->
                        val pointsList = mutableListOf<LatLng>()
                        for (i in routePoints.indices step 2) {
                            val lat = routePoints[i]
                            val lng = routePoints[i + 1]
                            pointsList.add(LatLng(lat, lng))
                        }
                        RouteList.value = pointsList
                    }
                    Polyline(points = RouteList.value)
                }
            }
            Row {
             Button(onClick = {
                 //se obtiene la localizacion actual de su celular, por eso el permiso de gps
                 scope.launch(Dispatchers.IO) {
                     val priority = if (usePreciseLocation) {
                         Priority.PRIORITY_HIGH_ACCURACY
                     } else {
                         Priority.PRIORITY_BALANCED_POWER_ACCURACY
                     }
                     val result = locationClient.getCurrentLocation(
                         priority,
                         CancellationTokenSource().token,
                     ).await()
                     result?.let { fetchedLocation ->
                         markerState.position =
                             LatLng(fetchedLocation.latitude, fetchedLocation.longitude)
                     }
                 }
             },
             ) {
                 Text(text = "Casa de Myriam")
             }
                Button(
                    onClick = {
                        scope.launch(Dispatchers.IO) {
                            val priority = if (usePreciseLocation) {
                                Priority.PRIORITY_HIGH_ACCURACY
                            } else {
                                Priority.PRIORITY_BALANCED_POWER_ACCURACY
                            }
                            val result = locationClient.getCurrentLocation(
                                priority,
                                CancellationTokenSource().token,
                            ).await()
                            result?.let { fetchedLocation ->
                                markerState2.position =
                                    LatLng(fetchedLocation.latitude, fetchedLocation.longitude)
                            }
                        }
                    },
                ) {
                    Text(text = "Casa de JESSE")
                }
            }

        }
    }
}


//Se encuentra la posicion de los dos puntos con la key de la API
private fun createRoute(
    startLocation: LatLng,
    endLocation: LatLng,
    callback: (List<Double>) -> Unit,
) {
    val routePoints = mutableListOf<LatLng>()
    CoroutineScope(Dispatchers.IO).launch {
        val call = getRetrofit().create(ApiServirce::class.java)
            .getRoute(
                "5b3ce3597851110001cf6248876d8cad012d4964a23567b4aede9053",
                "${startLocation.longitude},${startLocation.latitude}",
                "${endLocation.longitude},${endLocation.latitude}"
            )
        if (call.isSuccessful) {
            drawRoute(call.body(), routePoints)
            val pointsList = routePoints.flatMap { listOf(it.latitude, it.longitude) }
            callback(pointsList)
            Log.i("route", "OK")

        } else {
            Log.i("route", "KO")
        }
    }
}
//Funcion de dibujo con las coordenadas que se rescataron con el json de la API
private fun drawRoute(routeResponse: RouteResponse?, routePoints: MutableList<LatLng>) {
    routeResponse?.feature?.firstOrNull()?.geometry?.coordinates?.forEach {
        val latLng = LatLng(it[1], it[0])
        routePoints.add(latLng)
    }
}

//se obtiene el llamada del retrofit
private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.openrouteservice.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


