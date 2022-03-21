package io.joaofig.vedmap.viewmodels

import io.joaofig.vedmap.models.Cluster
import io.kvision.state.ObservableValue

class ClusterListItem(val cluster: Cluster) : ViewModel() {
    val isSelected = ObservableValue(false)
}