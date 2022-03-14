package io.joaofig.vedmap.clients

import io.joaofig.vedmap.services.ClusterService
import io.joaofig.vedmap.states.ClusterListState

object ClusterClient {
    private val service = ClusterService()

    suspend fun getClusterBounds() = service.getClusterBounds()

    suspend fun getClusters() = service.getClusters().map { ClusterListState(it) }

    suspend fun getClusterPolygon(clusterId: Int) = service.getClusterPolygon(clusterId)
}