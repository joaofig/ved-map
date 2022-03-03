package io.joaofig.vedmap.views

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.clients.ClusterClient
import io.joaofig.vedmap.converters.GeoBoundsConverter
import io.kvision.html.Div
import io.kvision.maps.DefaultTileLayers
import io.kvision.maps.Maps
import io.kvision.utils.perc
import kotlinx.coroutines.launch

class MapView: Div() {
    private val map = createMap()

    init {
        add(map)
    }

    private fun createMap(): Maps {
        val map = Maps()

        map.height = 100.perc
        map.width = 100.perc

        AppScope.launch {
            val geoBounds = ClusterClient.getClusterBounds()
            val latLngBounds = GeoBoundsConverter.toLatLngBounds(geoBounds)

            map.configureLeafletMap {
                fitBounds(latLngBounds)
                addLayer(DefaultTileLayers.OpenStreetMap)
            }
        }
        return map
    }
}