package io.joaofig.vedmap.viewmodels

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.clients.ClusterClient
import kotlinx.coroutines.launch

object ViewModelHub {
    val map by lazy { MapViewModel() }
    val clusterList by lazy { loadClusters() }

    fun selectCluster(clusterId: Int, select: Boolean) {
        val clusterListItem = clusterList.clusters.find { it.cluster.id == clusterId }
        clusterListItem?.isSelected?.value = select
    }

    private fun loadClusters(): ClusterListViewModel {
        val viewModel = ClusterListViewModel()

        AppScope.launch {
            viewModel.initialize(ClusterClient.getClusters())
        }
        return viewModel
    }
}