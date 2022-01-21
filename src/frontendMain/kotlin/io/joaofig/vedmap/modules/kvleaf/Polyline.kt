package io.joaofig.vedmap.modules.kvleaf

open class Polyline(polyline: dynamic): Path(polyline) {

    fun getCenter(): LatLng {
        return LatLng(jsObject.getCenter())
    }

    fun getBounds(): LatLngBounds {
        return LatLngBounds(jsObject.getBounds())
    }
}