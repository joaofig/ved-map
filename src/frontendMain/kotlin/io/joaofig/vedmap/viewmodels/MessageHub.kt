package io.joaofig.vedmap.viewmodels

import io.joaofig.vedmap.messages.ClusterMessage

object MessageHub {
    val clusterMessenger = Messenger<ClusterMessage>()
}