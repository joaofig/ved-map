package io.joaofig.vedmap.messages

import io.joaofig.vedmap.models.Cluster

enum class ClusterAction {
    SELECTED,
    DESELECTED
}

data class ClusterMessage(
    val cluster: Cluster,
    val action: ClusterAction
)