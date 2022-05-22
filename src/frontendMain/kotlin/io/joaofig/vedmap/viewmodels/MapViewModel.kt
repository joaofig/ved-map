package io.joaofig.vedmap.viewmodels

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.clients.ClusterClient
import io.joaofig.vedmap.messages.ClusterAction
import io.joaofig.vedmap.messages.ClusterMessage
import io.joaofig.vedmap.models.Cluster
import io.joaofig.vedmap.models.GeoMultiPolygon
import io.joaofig.vedmap.models.Trajectory
import io.joaofig.vedmap.repositories.ClusterRepository
import io.joaofig.vedmap.repositories.TripRepository
import io.kvision.maps.externals.leaflet.geo.LatLng
import io.kvision.state.ObservableListWrapper
import io.kvision.state.ObservableValue
import kotlinx.coroutines.launch

class MapViewModel: ViewModel() {
    val clusters = ObservableListWrapper<MapCluster>(mutableListOf())
    val clusterPoints = ObservableListWrapper<ClusterPoint>(mutableListOf())
    val tripClusterIni = ObservableValue<MapCluster?>(null)
    val tripClusterEnd = ObservableValue<MapCluster?>(null)
    val trajectories = ObservableListWrapper<Trajectory>(mutableListOf())

    init {
        MessageHub.clusterMessenger.subscribe { handleClusterMessage(it) }
    }

    fun showInboundClusters(clusterId: Int) {
        AppScope.launch {
            val clusters = ClusterRepository.getInboundClusters(clusterId)
            if (clusters != null) {
                for (cluster in clusters) {
                    ViewModelHub.selectCluster(cluster, true)
                }
            }
        }
    }

    fun showOutboundClusters(clusterId: Int) {
        AppScope.launch {
            val clusters = ClusterRepository.getOutboundClusters(clusterId)
            if (clusters != null) {
                for (cluster in clusters) {
                    ViewModelHub.selectCluster(cluster, true)
                }
            }
        }
    }

    fun showClusterPoints(clusterId: Int) {
        AppScope.launch {
            val points = ClusterClient.getClusterPoints(clusterId)
            clusterPoints.addAll(points.map { ClusterPoint(clusterId, LatLng(it.latitude, it.longitude))})
        }
    }

    fun hideClusterPoints(clusterId: Int) {
        clusterPoints.removeAll { it.clusterId == clusterId }
    }

    private fun handleClusterMessage(msg: ClusterMessage) {
        when (msg.action) {
            ClusterAction.SELECTED -> {
                AppScope.launch {
                    val polygon = ClusterRepository.getClusterPolygon(msg.cluster.id)
                    if (polygon != null) {
                        val mapCluster = MapCluster(msg.cluster, polygon)
                        clusters.add(mapCluster)
                    }
                }
            }

            ClusterAction.DESELECTED -> {
                val element = clusters.find { it.cluster.id == msg.cluster.id }
                if (element != null) {
                    clusters.remove(element)
                    clusterPoints.removeAll { it.clusterId == msg.cluster.id }
                }
            }

            ClusterAction.SHOW_START -> {
                AppScope.launch {
                    val polygon = ClusterRepository.getClusterPolygon(msg.cluster.id)
                    if (polygon != null) {
                        tripClusterIni.value = MapCluster(msg.cluster, polygon)
                        tripClusterEnd.value = null
                    }
                }
            }

            ClusterAction.SHOW_END -> {
                AppScope.launch {
                    val polygon = ClusterRepository.getClusterPolygon(msg.cluster.id)
                    if (polygon != null) {
                        tripClusterEnd.value = MapCluster(msg.cluster, polygon)
                    }

                    val clusterIni = tripClusterIni.value?.cluster?.id ?: -1
                    val clusterEnd = tripClusterEnd.value?.cluster?.id ?: -1

                    if (clusterIni >= 0 && clusterEnd >= 0) {
                        val trips = TripRepository.getClusterTrips(clusterIni, clusterEnd)
                        if (trips != null) {
                            for (trip in trips) {
                                val trajectory = TripRepository.getSingleVehicleTrip(trip)

                                if (trajectory != null) {
                                    trajectories.add(trajectory)
                                }
                            }
                        }
                    }
                }
            }

            ClusterAction.CLEAR_START -> {
                tripClusterIni.value = null
                tripClusterEnd.value = null
                trajectories.clear()
            }

            ClusterAction.CLEAR_END -> {
                tripClusterEnd.value = null
                trajectories.clear()
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
