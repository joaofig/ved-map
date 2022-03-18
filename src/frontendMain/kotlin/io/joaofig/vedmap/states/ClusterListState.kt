package io.joaofig.vedmap.states

import io.joaofig.vedmap.models.Cluster
import io.kvision.state.ObservableValue

class ClusterListState(val cluster: Cluster) {
    val isSelected = ObservableValue(false)
}
