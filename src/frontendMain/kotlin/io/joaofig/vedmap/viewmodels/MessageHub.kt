package io.joaofig.vedmap.viewmodels

import io.joaofig.vedmap.messages.ClusterAction
import io.joaofig.vedmap.messages.ClusterMessage
import io.joaofig.vedmap.models.Cluster

object MessageHub {
    val clusterMessenger = Messenger<ClusterMessage>()

    fun send(cluster: Cluster, action: ClusterAction, sender: ViewModel) =
        clusterMessenger.send(ClusterMessage(cluster, action, sender))
}