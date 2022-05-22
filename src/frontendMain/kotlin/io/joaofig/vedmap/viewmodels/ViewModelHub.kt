package io.joaofig.vedmap.viewmodels

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.repositories.ClusterRepository
import kotlinx.coroutines.launch

object ViewModelHub {
    val map by lazy { MapViewModel() }
    val clusterList by lazy { createClusterListViewModel() }
    val trip by lazy { createTripViewModel() }

    init {
    }

    fun selectCluster(clusterId: Int, select: Boolean) {
        val clusterListItem = clusterList.clusters.find { it.cluster.id == clusterId }
        clusterListItem?.isSelected?.value = select
    }

    private fun createClusterListViewModel(): ClusterListViewModel {
        val viewModel = ClusterListViewModel()

        AppScope.launch {
            viewModel.initialize(ClusterRepository.getClusters())
        }
        return viewModel
    }

    private fun createTripViewModel(): TripViewModel {
        val viewModel = TripViewModel()

        AppScope.launch {
            viewModel.initialize(ClusterRepository.getClusters())
        }
        return viewModel
    }
}