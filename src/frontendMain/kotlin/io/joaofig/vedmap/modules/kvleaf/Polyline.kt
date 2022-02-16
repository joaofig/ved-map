package io.joaofig.vedmap.modules.kvleaf

open class Polyline(polyline: dynamic): Path(polyline) {

    fun add(latLng: LatLng): Polyline {
        jsObject.addLatLng(latLng.toDynamic())
        return this
    }

    fun getCenter(): LatLng {
        return LatLng(jsObject.getCenter())
    }

    fun getLatLngs(): Array<LatLng> {
        val jsLatLongs = jsObject.getLatLngs()
        val n = jsLatLongs.length as Int

        return (0 until n)
            .map { LatLng(jsLatLongs[it]) }
            .toTypedArray()
    }

    fun getBounds(): LatLngBounds {
        return LatLngBounds(jsObject.getBounds())
    }

    val isEmpty: Boolean
        get() = jsObject.isEmpty() as Boolean

    fun setLatLngs(latLngs: Array<LatLng>): Polyline {
        val arr = latLngs.map { it.toDynamic() }.toTypedArray()
        jsObject.addLatLngs(arr)
        return this
    }
}