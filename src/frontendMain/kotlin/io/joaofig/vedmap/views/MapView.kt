package io.joaofig.vedmap.views

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.clients.ClusterClient
import io.joaofig.vedmap.controls.CtxMenuItem
import io.joaofig.vedmap.controls.CtxPolylineOptions
import io.joaofig.vedmap.controls.MapContextMenu
import io.joaofig.vedmap.converters.toLatLngBounds
import io.joaofig.vedmap.converters.toMultiPolygon
import io.joaofig.vedmap.viewmodels.ClusterPoint
import io.joaofig.vedmap.viewmodels.MapCluster
import io.joaofig.vedmap.viewmodels.ViewModelHub
import io.kvision.dropdown.*
import io.kvision.html.Div
import io.kvision.maps.DefaultTileLayers
import io.kvision.maps.Maps
import io.kvision.maps.externals.geojson.MultiPolygon
import io.kvision.maps.externals.leaflet.events.LeafletMouseEvent
import io.kvision.maps.externals.leaflet.layer.vector.Circle
import io.kvision.maps.externals.leaflet.layer.vector.CircleMarker
import io.kvision.maps.externals.leaflet.layer.vector.Polygon
import io.kvision.utils.perc
import kotlinx.coroutines.launch

class MapView: Div() {
    private val map = createMap()
    private val contextMenu = MapContextMenu()
    private val viewModel = ViewModelHub.map
    private val clusters: MutableMap<Int, Polygon<MultiPolygon>> = mutableMapOf()
    private val clusterPoints: MutableMap<Int, List<CircleMarker>> = mutableMapOf()

    init {
        add(map)
        viewModel.clusters.subscribe { updateClusters(it) }
        viewModel.clusterPoints.subscribe { updateClusterPoints(it) }
    }

    private fun updateClusters(mapClusters: List<MapCluster>) {
        val newIdSet = mapClusters.map { it.cluster.id }.toSet()
        val oldIdSet = clusters.keys
        val toInsert = newIdSet.minus(oldIdSet)
        val toRemove = oldIdSet.minus(newIdSet)

        // Remove clusters from map
        for (id in toRemove) {
            clusters[id]?.removeEventListener("contextmenu")
            clusters[id]?.remove()
            clusters.remove(id)
        }

        // Add clusters to map
        for (id in toInsert) {
            val mapCluster = mapClusters.find { it.cluster.id == id }
            if (mapCluster != null) {
                val options = CtxPolylineOptions().apply {
                    contextmenu = true
                    contextmenuWidth = 140
                    contextmenuItems = arrayOf(
                        CtxMenuItem("Show Points") {
                            ViewModelHub.map.showClusterPoints(mapCluster.cluster.id)
                        },
                        CtxMenuItem("Hide Points") {
                            ViewModelHub.map.hideClusterPoints(mapCluster.cluster.id)
                        },
                        CtxMenuItem(separator = true),
                        CtxMenuItem("Hide Cluster") {
                            ViewModelHub.selectCluster(mapCluster.cluster.id, false)
                        }
                    )
                }
                val cluster = mapCluster.polygon.toMultiPolygon(options)
                cluster.bindPopup(mapCluster.cluster.name)

//                val fn: LeafletMouseEventHandlerFn = { event: LeafletMouseEvent ->
//                    handleClusterContextMenu(event, mapCluster) }
//                cluster.addEventListener("contextmenu", fn)
//
                clusters[id] = cluster
                map.leafletMap { cluster.addTo(this) }
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

    private fun handleClusterContextMenu(
        event: LeafletMouseEvent,
        mapCluster: MapCluster
    ) {
        val menu = contextMenu {
//            fontSize = 10.pt
//            cursor = Cursor.POINTER
//            padding = 2.px

            dropDown("Show", forDropDown = true) {
//                fontSize = 10.pt
//                cursor = Cursor.POINTER
//                padding = 2.px
//
                ddLink("Inbound clusters").onClick {
                    ViewModelHub.map.showInboundClusters(mapCluster.cluster.id)
                }
                ddLink("Outbound clusters").onClick {
                    ViewModelHub.map.showOutboundClusters(mapCluster.cluster.id)
                }
                separator()
                ddLink("Cluster points").onClick {
                    ViewModelHub.map.showClusterPoints(mapCluster.cluster.id)
                }
            }
            dropDown("Hide", forDropDown = true) {
                ddLink("Cluster").onClick {
                    ViewModelHub.selectCluster(mapCluster.cluster.id, false)
                }
                ddLink("Cluster points").onClick {
                    ViewModelHub.map.hideClusterPoints(mapCluster.cluster.id)
                }
            }
            cmLink("Properties").onClick {  }
        }
        menu.positionMenu(event.originalEvent)
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