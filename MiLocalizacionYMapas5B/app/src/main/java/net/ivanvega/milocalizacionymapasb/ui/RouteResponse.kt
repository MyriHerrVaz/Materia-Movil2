package net.ivanvega.milocalizacionymapasb.ui

import com.google.android.gms.common.Feature
import com.google.gson.annotations.SerializedName


data class RouteResponse(@SerializedName("features")val feature:List<Feature>)
