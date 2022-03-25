package io.joaofig.vedmap.clients

import io.joaofig.vedmap.services.ClusterService
import io.joaofig.vedmap.viewmodels.ClusterListItem

object ClusterClient {
    private val service = ClusterService()

    suspend fun getClusterBounds() = service.getClusterBounds()

    suspend fun getClusters() = service.getClusters().map { ClusterListItem(it) }

    suspend fun getClusterPolygon(clusterId: Int) = service.getClusterPolygon(clusterId)

    suspend fun getInboundClusters(clusterId: Int) = service.getInboundClusters(clusterId)

    suspend fun getOutboundClusters(clusterId: Int) = service.getOutboundClusters(clusterId)
}