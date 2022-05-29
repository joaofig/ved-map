package io.joaofig.vedmap.views

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.clients.ClusterClient
import io.joaofig.vedmap.controls.ContextMenuMap
import io.joaofig.vedmap.controls.ContextMenuPolylineOptions
import io.joaofig.vedmap.controls.CtxMenuItem
import io.joaofig.vedmap.converters.toLatLngBounds
import io.joaofig.vedmap.converters.toMultiPolygon
import io.joaofig.vedmap.converters.toPolyline
import io.joaofig.vedmap.models.Trajectory
import io.joaofig.vedmap.viewmodels.ClusterPoint
import io.joaofig.vedmap.viewmodels.MapCluster
import io.joaofig.vedmap.viewmodels.ViewModelHub
import io.kvision.html.Div
import io.kvision.maps.DefaultTileLayers
import io.kvision.maps.externals.geojson.LineString
import io.kvision.maps.externals.geojson.MultiPolygon
import io.kvision.maps.externals.leaflet.layer.vector.Circle
import io.kvision.maps.externals.leaflet.layer.vector.CircleMarker
import io.kvision.maps.externals.leaflet.layer.vector.Polygon
import io.kvision.maps.externals.leaflet.layer.vector.Polyline
import io.kvision.utils.perc
import kotlinx.coroutines.launch

class MapView: Div() {
    private val map = createMap()
    private val viewModel = ViewModelHub.map
    private val clusters: MutableMap<Int, Polygon<MultiPolygon>> = mutableMapOf()
    private val clusterPoints: MutableMap<Int, List<CircleMarker>> = mutableMapOf()
    private var tripClusterIni: Polygon<MultiPolygon>? = null
    private var tripClusterEnd: Polygon<MultiPolygon>? = null
    private val trajectories: MutableMap<Int, Polyline<LineString>> = mutableMapOf()

    init {
        add(map)
        viewModel.clusters.subscribe { updateClusters(it) }
        viewModel.clusterPoints.subscribe { updateClusterPoints(it) }
        viewModel.tripClusterIni.subscribe { updateTripClusterIni(it) }
        viewModel.tripClusterEnd.subscribe { updateTripClusterEnd(it) }
        viewModel.trajectories.subscribe { updateTrajectories(it) }
    }

    private fun createTripCluster(mapCluster: MapCluster): Polygon<MultiPolygon> {
//        val options = PolylineOptions().apply {
//            contextmenu = true
//            contextmenuItems = arrayOf(
//                CtxMenuItem("Show Points") {
//                    ViewModelHub.map.showClusterPoints(mapCluster.cluster.id)
//                },
//                CtxMenuItem("Hide Points") {
//                    ViewModelHub.map.hideClusterPoints(mapCluster.cluster.id)
//                }
//            )
//        }
        val cluster = mapCluster.polygon.toMultiPolygon()
        cluster.bindPopup(mapCluster.cluster.name)
        return cluster
    }

    private fun updateTripClusterIni(cluster: MapCluster?) {
        tripClusterIni?.remove()
        tripClusterIni = null
        if (cluster != null) {
            val polygon = createTripCluster(cluster)
            map.leafletMap {
                polygon.addTo(this)
                tripClusterIni = polygon
            }
        }
    }

    private fun updateTripClusterEnd(cluster: MapCluster?) {
        tripClusterEnd?.remove()
        tripClusterEnd = null
        if (cluster != null) {
            val polygon = createTripCluster(cluster)
            map.leafletMap {
                polygon.addTo(this)
                tripClusterEnd = polygon
            }
        }
    }

    private fun updateClusters(mapClusters: List<MapCluster>) {
        val newIdSet = mapClusters.map { it.cluster.id }.toSet()
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
            val mapCluster = mapClusters.find { it.cluster.id == id }
            if (mapCluster != null) {
//                val options = obj<ContextMenuPolylineOptions>() {
//                    contextmenu = true
//                    contextmenuItems = arrayOf(
//                        CtxMenuItem("Show Points") {
//                            ViewModelHub.map.showClusterPoints(mapCluster.cluster.id)
//                        },
//                        CtxMenuItem("Hide Points") {
//                            ViewModelHub.map.hideClusterPoints(mapCluster.cluster.id)
//                        },
//                        CtxMenuItem(separator = true),
//                        CtxMenuItem("Inbound Clusters") {
//                            ViewModelHub.map.showInboundClusters(mapCluster.cluster.id)
//                        },
//                        CtxMenuItem("Outbound Clusters") {
//                            ViewModelHub.map.showOutboundClusters(mapCluster.cluster.id)
//                        },
//                        CtxMenuItem(separator = true),
//                        CtxMenuItem("Hide Cluster") {
//                            ViewModelHub.selectCluster(mapCluster.cluster.id, false)
//                        }
//                    )
//                }
                val cluster = mapCluster.polygon.toMultiPolygon<ContextMenuPolylineOptions> {
                    contextmenu = true
                    contextmenuItems = arrayOf(
                        CtxMenuItem(separator = true),
                        CtxMenuItem("Show Points") {
                            ViewModelHub.map.showClusterPoints(mapCluster.cluster.id)
                        },
                        CtxMenuItem("Hide Points") {
                            ViewModelHub.map.hideClusterPoints(mapCluster.cluster.id)
                        },
                        CtxMenuItem(separator = true),
                        CtxMenuItem("Inbound Clusters") {
                            ViewModelHub.map.showInboundClusters(mapCluster.cluster.id)
                        },
                        CtxMenuItem("Outbound Clusters") {
                            ViewModelHub.map.showOutboundClusters(mapCluster.cluster.id)
                        },
                        CtxMenuItem(separator = true),
                        CtxMenuItem("Hide Cluster") {
                            ViewModelHub.selectCluster(mapCluster.cluster.id, false)
                        }
                    )
                }
//                cluster.bindPopup(mapCluster.cluster.name)

                clusters[id] = cluster
                map.leafletMap { cluster.addTo(this) }
            }
        }
    }

    private fun updateTrajectories(updated: List<Trajectory>) {
        val newIdSet = updated.map { it.tripId }.toSet()
        val oldIdSet = trajectories.keys
        val toInsert = newIdSet.minus(oldIdSet)
        val toRemove = oldIdSet.minus(newIdSet)

        for (id in toRemove) {
            trajectories[id]?.remove()
            trajectories.remove(id)
        }

        for (id in toInsert) {
            val trajectory = updated.find { id == it.tripId }
            if (trajectory != null) {
                val polyline = trajectory.toPolyline().apply {
                    with(options) {
                        color = "#800000"
                    }
                }
                trajectories[id] = polyline
                map.leafletMap { polyline.addTo(this) }
            }
        }
    }

    private fun updateClusterPoints(mapPoints: List<ClusterPoint>) {
        val newIdSet = mapPoints.map { it.clusterId }.toSet()
        val oldIdSet = clusterPoints.keys
        val toInsert = newIdSet.minus(oldIdSet)
        val toRemove = oldIdSet.minus(newIdSet)

        for (id in toRemove) {
            clusterPoints[id]?.forEach { it.remove() }
            clusterPoints.remove(id)
        }

        for (id in toInsert) {
            val pts = mapPoints.filter { it.clusterId == id }
            val circles = pts.map {
                Circle(it.location)
                    .setRadius(1)
                    .apply {
                        with(options) {
                            color = "red"
                        }
                    }
            }

            map.leafletMap {
                circles.forEach { it.addTo(this) }
            }
            clusterPoints[id] = circles
        }
    }

    private fun createMap(): ContextMenuMap {
        val map = ContextMenuMap()

        map.height = 100.perc
        map.width = 100.perc
        with(map.options) {
            contextmenu = true
            contextmenuItems = arrayOf(
                CtxMenuItem("Zoom In") {
                    zoomIn()
                },
                CtxMenuItem("Zoom Out") {
                    zoomOut()
                }
            )
        }

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

    private fun zoomIn() {
        map.leafletMap {
            zoomIn()
        }
    }

    private fun zoomOut() {
        map.leafletMap {
            zoomOut()
        }
    }
}