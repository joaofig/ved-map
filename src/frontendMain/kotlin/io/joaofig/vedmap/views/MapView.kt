package io.joaofig.vedmap.views

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.clients.ClusterClient
import io.joaofig.vedmap.converters.toLatLngBounds
import io.joaofig.vedmap.viewmodels.MapCluster
import io.joaofig.vedmap.viewmodels.ViewModelHub
import io.kvision.html.Div
import io.kvision.maps.DefaultTileLayers
import io.kvision.maps.Maps
import io.kvision.utils.perc
import kotlinx.coroutines.launch

class MapView: Div() {
    private val map = createMap()
    private val viewModel = ViewModelHub.map

    init {
        add(map)
        viewModel.clusters.subscribe { drawClusters(it) }
    }

    private fun drawClusters(clusters: List<MapCluster>) {

    }

    private fun createMap(): Maps {
        val map = Maps()

        map.height = 100.perc
        map.width = 100.perc

        AppScope.launch {
            val geoBounds = ClusterClient.getClusterBounds()
            val latLngBounds = geoBounds.toLatLngBounds()

            map.configureLeafletMap {
                fitBounds(latLngBounds)
                addLayer(DefaultTileLayers.OpenStreetMap)
            }
        }
        return map
    }
}