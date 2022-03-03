package io.joaofig.vedmap.views

import io.kvision.html.Div
import io.kvision.maps.DefaultTileLayers
import io.kvision.maps.Maps
import io.kvision.maps.externals.leaflet.geo.LatLng
import io.kvision.utils.perc

class MapView: Div() {
    private val map = createMap()

    init {
        add(map)
    }

    private fun createMap(): Maps {
        val map = Maps()

        map.height = 100.perc
        map.width = 100.perc
        map.configureLeafletMap {
            setView(LatLng(0, 0), 1)
            addLayer(DefaultTileLayers.OpenStreetMap)
        }

        return map
    }
}