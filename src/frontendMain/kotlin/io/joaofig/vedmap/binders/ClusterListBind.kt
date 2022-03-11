package io.joaofig.vedmap.binders

import io.joaofig.vedmap.models.Cluster
import io.kvision.state.ObservableValue

class ClusterListBind(cluster: Cluster) {
    val id: Int = cluster.id
    val name: String = cluster.name
    val isSelected = ObservableValue(false)
}