package io.joaofig.vedmap.clients

import io.joaofig.vedmap.binders.ClusterListBind
import io.joaofig.vedmap.services.ClusterService

object ClusterClient {
    private val service = ClusterService()

    suspend fun getClusterBounds() = service.getClusterBounds()

    suspend fun getClusters() = service.getClusters().map { ClusterListBind(it) }
}