package io.joaofig.vedmap.viewmodels

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.clients.ClusterClient
import io.joaofig.vedmap.converters.toMultiPolygon
import io.joaofig.vedmap.messages.ClusterAction
import io.joaofig.vedmap.messages.ClusterMessage
import io.kvision.maps.externals.geojson.MultiPolygon
import io.kvision.maps.externals.leaflet.layer.vector.Polygon
import io.kvision.state.ObservableListWrapper
import kotlinx.coroutines.launch

class MapViewModel: ViewModel() {
    val clusters = ObservableListWrapper<MapCluster>(mutableListOf())

    init {
        MessageHub.clusterMessenger.subscribe { handleClusterMessage(it) }
    }

    private fun handleClusterMessage(msg: ClusterMessage) {
        when (msg.action) {
            ClusterAction.SELECTED -> {
                AppScope.launch {
                    val geoPolygon = ClusterClient.getClusterPolygon(msg.cluster.id)
                    val polygon = geoPolygon.toMultiPolygon()
                    val mapCluster = MapCluster(msg.cluster.id, polygon)
                    clusters.add(mapCluster)
                }
            }
            ClusterAction.DESELECTED -> {
                val element = clusters.find { it.id == msg.cluster.id }
                if (element != null) {
                    clusters.remove(element)
                }
            }
        }
    }
}

data class MapCluster(
    val id: Int,
    val polygon: Polygon<MultiPolygon>
)
