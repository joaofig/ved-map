package io.joaofig.vedmap.states

import io.joaofig.vedmap.models.Cluster
import io.joaofig.vedmap.models.GeoMultiPolygon
import io.kvision.state.ObservableValue

class ClusterListState(cluster: Cluster) {
    val id: Int = cluster.id
    val name: String = cluster.name
    val isSelected = ObservableValue(false)
    val multiPolygon = ObservableValue(GeoMultiPolygon(listOf()))
}
