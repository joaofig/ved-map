package io.joaofig.vedmap.viewmodels

object ViewModelHub {
    val map by lazy { MapViewModel() }
    val clusterList by lazy { ClusterListViewModel() }
}