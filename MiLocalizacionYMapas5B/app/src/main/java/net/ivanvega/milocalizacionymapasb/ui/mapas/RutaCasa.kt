package net.ivanvega.milocalizacionymapasb.ui.mapas

import androidx.compose.runtime.Composable
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

)
