package io.joaofig.vedmap.messages

import io.joaofig.vedmap.models.Cluster
import io.joaofig.vedmap.viewmodels.ViewModel

enum class ClusterAction {
    SELECTED,
    DESELECTED,
    SHOW_START,
    SHOW_END,
    CLEAR_START,
    CLEAR_END
}

data class ClusterMessage(
    val cluster: Cluster,
    val action: ClusterAction,
    override val sender: ViewModel
) : Message(sender) {

}