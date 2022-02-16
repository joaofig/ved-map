package io.joaofig.vedmap.modules.kvleaf

class MapConversions(private val map: dynamic) {
    fun getZoomScale(toZoom: Number, fromZoom: Number): Number = map.getZoomScale(toZoom, fromZoom) as Number
    fun getScaleZoom(scale: Number, fromZoom: Number): Number = map.getScaleZoom(scale, fromZoom) as Number
    fun project(latLng: LatLng, zoom: Number) = Point(map.project(latLng.toDynamic(), zoom))
    fun unproject(point: Point, zoom: Number) = LatLng(map.unproject(point.toDynamic(), zoom))
    fun layerPointToLatLng(point: Point) = LatLng(map.layerPointToLatLng(point.toDynamic()))
}