package io.joaofig.vedmap.viewmodels

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.clients.ClusterClient
import io.joaofig.vedmap.messages.ClusterAction
import io.joaofig.vedmap.messages.ClusterMessage
import io.joaofig.vedmap.models.Cluster
import io.joaofig.vedmap.models.GeoMultiPolygon
import io.kvision.maps.externals.leaflet.geo.LatLng
import io.kvision.state.ObservableListWrapper
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MapViewModel: ViewModel() {
    val clusters = ObservableListWrapper<MapCluster>(mutableListOf())
    val clusterPoints = ObservableListWrapper<ClusterPoint>(mutableListOf())

    init {
        MessageHub.clusterMessenger.subscribe { handleClusterMessage(it) }
    }

    fun showInboundClusters(clusterId: Int) {
        AppScope.launch {
            val clusters = ClusterClient.getInboundClusters(clusterId)
            for (cluster in clusters) {
                ViewModelHub.selectCluster(cluster, true)
            }
        }
    }

    fun showOutboundClusters(clusterId: Int) {
        AppScope.launch {
            val clusters = ClusterClient.getOutboundClusters(clusterId)
            for (cluster in clusters) {
                ViewModelHub.selectCluster(cluster, true)
            }
        }
    }

    fun showClusterPoints(clusterId: Int) {
        AppScope.launch {
            val points = ClusterClient.getClusterPoints(clusterId)
            clusterPoints.addAll(points.map { ClusterPoint(clusterId, LatLng(it.latitude, it.longitude))})
        }
    }

    private fun handleClusterMessage(msg: ClusterMessage) {
        when (msg.action) {
            ClusterAction.SELECTED -> {
                val mapCluster = AppScope.async {
                    val geoPolygon = ClusterClient.getClusterPolygon(msg.cluster.id)
                    MapCluster(msg.cluster, geoPolygon)
                }

                AppScope.launch {
                    clusters.add(mapCluster.await())
                }
           }
            ClusterAction.DESELECTED -> {
                val element = clusters.find { it.cluster.id == msg.cluster.id }
                if (element != null) {
                    clusters.remove(element)
                }
            }
        }
    }
}

data class MapCluster(
    val cluster: Cluster,
    val polygon: GeoMultiPolygon
)

data class ClusterPoint(
    val clusterId: Int,
    val location: LatLng
)
