package io.joaofig.vedmap.views

import io.joaofig.vedmap.modules.Leaflet
import io.kvision.html.Div
import io.kvision.maps.BaseLayerProvider
import io.kvision.maps.CRS
import io.kvision.utils.perc

class MapView: Div() {
    val map = createMap()

    init {
        add(map)
    }

    private fun createMap(): Leaflet {
        val map = Leaflet(
            lat = 0.0,
            lng= 0.0,
            zoom = 4,
            baseLayerProvider = BaseLayerProvider.OSM,
            crs = CRS.EPSG3857)
        map.height = 100.perc
        map.width = 100.perc
        return map
    }
}