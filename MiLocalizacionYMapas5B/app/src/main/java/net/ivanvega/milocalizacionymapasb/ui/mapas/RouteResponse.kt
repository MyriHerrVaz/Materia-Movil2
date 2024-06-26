package net.ivanvega.milocalizacionymapasb.ui.mapas

import com.google.android.gms.common.Feature
import com.google.gson.annotations.SerializedName


data class RouteResponse(@SerializedName("features")val feature:List<Feature>)
data class Feature(@SerializedName("geometry") val geometry:Geometry)
data class Geometry(@SerializedName("coordinates") val coordinates: List<List<Double>> )