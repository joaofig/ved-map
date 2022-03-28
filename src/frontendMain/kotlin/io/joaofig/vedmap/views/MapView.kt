package io.joaofig.vedmap.views

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.clients.ClusterClient
import io.joaofig.vedmap.converters.toLatLngBounds
import io.joaofig.vedmap.converters.toMultiPolygon
import io.joaofig.vedmap.viewmodels.MapCluster
import io.joaofig.vedmap.viewmodels.ViewModelHub
import io.kvision.dropdown.*
import io.kvision.html.Div
import io.kvision.maps.DefaultTileLayers
import io.kvision.maps.Maps
import io.kvision.maps.externals.geojson.MultiPolygon
import io.kvision.maps.externals.leaflet.events.LeafletMouseEvent
import io.kvision.maps.externals.leaflet.events.LeafletMouseEventHandlerFn
import io.kvision.maps.externals.leaflet.layer.vector.Polygon
import io.kvision.utils.perc
import kotlinx.coroutines.launch

class MapView: Div() {
    private val map = createMap()
    private val viewModel = ViewModelHub.map
    private val clusters: MutableMap<Int, Polygon<MultiPolygon>> = mutableMapOf()

    init {
        add(map)
        viewModel.clusters.subscribe { updateClusters(it) }
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
                val cluster = mapCluster.polygon.toMultiPolygon()
                cluster.bindPopup(mapCluster.cluster.name)

                val fn: LeafletMouseEventHandlerFn = { e -> handleClusterContextMenu(e, mapCluster) }
                cluster.on("contextmenu", fn, cluster)

                clusters[id] = cluster
                map.leafletMap { cluster.addTo(this) }
            }
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

            cmLink("Hide").onClick {
                ViewModelHub.selectCluster(mapCluster.cluster.id, false)
            }
            dropDown("Show", forDropDown = true) {
//                fontSize = 10.pt
//                cursor = Cursor.POINTER
//                padding = 2.px
//
                ddLink("Cluster points").onClick {
                    ViewModelHub.map.showOutboundClusters(mapCluster.cluster.id)
                }
                ddLink("Inbound clusters").onClick {
                    ViewModelHub.map.showInboundClusters(mapCluster.cluster.id)
                }
                ddLink("Outbound clusters").onClick {
                    ViewModelHub.map.showOutboundClusters(mapCluster.cluster.id)
                }
                separator()
                ddLink("Cluster points").onClick {  }
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