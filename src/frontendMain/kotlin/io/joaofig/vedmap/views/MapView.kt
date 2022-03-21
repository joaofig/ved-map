package io.joaofig.vedmap.views

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.clients.ClusterClient
import io.joaofig.vedmap.converters.toLatLngBounds
import io.joaofig.vedmap.converters.toMultiPolygon
import io.joaofig.vedmap.viewmodels.MapCluster
import io.joaofig.vedmap.viewmodels.ViewModelHub
import io.kvision.html.Div
import io.kvision.maps.DefaultTileLayers
import io.kvision.maps.Maps
import io.kvision.maps.externals.geojson.MultiPolygon
import io.kvision.maps.externals.leaflet.layer.vector.Polygon
import io.kvision.utils.perc
import kotlinx.coroutines.launch

class MapView: Div() {
    private val map = createMap()
    private val viewModel = ViewModelHub.map
    private val clusters: MutableMap<Int, Polygon<MultiPolygon>> = mutableMapOf()

    init {
        add(map)
        viewModel.clusters.subscribe { drawClusters(it) }
    }

    private fun drawClusters(mapClusters: List<MapCluster>) {
        val newIdSet = mapClusters.map { it.id }.toSet()
        val oldIdSet = clusters.keys
        val toInsert = newIdSet.minus(oldIdSet)
        val toRemove = oldIdSet.minus(newIdSet)

        // Remove clusters from map
        for (id in toRemove) {
            clusters[id]?.remove()
            clusters.remove(id)
        }

        // Add clusters to map
        for (id in toInsert) {
            val mapCluster = mapClusters.find { it.id == id }
            if (mapCluster != null) {
                val cluster = mapCluster.polygon.toMultiPolygon()
                clusters[id] = cluster
                map.leafletMap { cluster.addTo(this) }
            }
        }
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