package io.joaofig.vedmap.viewmodels

import io.joaofig.vedmap.AppScope
import io.joaofig.vedmap.messages.ClusterAction
import io.joaofig.vedmap.models.Cluster
import io.joaofig.vedmap.repositories.ClusterRepository
import io.kvision.state.ObservableListWrapper
import io.kvision.state.ObservableValue
import kotlinx.coroutines.launch

class TripViewModel : ViewModel() {
    private val clusterList: MutableList<ClusterListItem> = mutableListOf()
    val clusters: ObservableListWrapper<ClusterListItem> = ObservableListWrapper(mutableListOf())
    val children: ObservableListWrapper<ClusterListItem> = ObservableListWrapper(mutableListOf())
    val sortAscending = ObservableValue<Boolean?>(null)
    val selectedCluster = ObservableValue<ClusterListItem?>(null)
    val selectedChild = ObservableValue<ClusterListItem?>(null)

    init {
        selectedCluster.subscribe { onClusterSelected(it) }
        selectedChild.subscribe { onChildSelected(it) }
    }

    fun initialize(list: List<Cluster>) {
        clusterList.addAll(list.map { ClusterListItem(it) })
        clusters.addAll(clusterList)
    }

    var clusterFilter: String = ""
        set(filter) {
            field = filter
//            sortAndFilter()
        }

    var childrenFilter: String = ""
        set(filter) {
            field = filter
//            sortAndFilter()
        }

    private fun sortAndFilter(nameFilter: String, list: ObservableListWrapper<ClusterListItem>) {
        list.clear()
        list.addAll(sortClustersByName(sortAscending.value, filterClusters(nameFilter, clusterList)))
    }

    private fun sortClustersByName(
        sort: Boolean?,
        list: MutableList<ClusterListItem>
    ): MutableList<ClusterListItem> {
        return when (sort) {
            null -> list
            true -> list.sortedBy { it.cluster.name }.toMutableList()
            false -> list.sortedByDescending { it.cluster.name }.toMutableList()
        }
    }

    private fun filterClusters(
        filter: String,
        list: MutableList<ClusterListItem>
    ): MutableList<ClusterListItem> {
        return if (filter == "") {
            list
        } else {
            list.filter { it.cluster.name.contains(filter, true) }.toMutableList()
        }
    }

    fun isClusterSelected(cluster: ClusterListItem?): Boolean {
        return if (cluster == null) {
            false
        } else {
            cluster == selectedCluster.value
        }
    }

    fun isChildSelected(cluster: ClusterListItem?): Boolean {
        return if (cluster == null) {
            false
        } else {
            cluster == selectedChild.value
        }
    }

    private fun loadChildren(clusterId: Int) {
        AppScope.launch {
            val ids = ClusterRepository.getOutboundClusters(clusterId)

            if (ids != null) {
                children.addAll(clusters.filter { ids.contains(it.cluster.id) && clusterId != it.cluster.id })
            }
        }
    }

    private fun onClusterSelected(cluster: ClusterListItem?) {
        clusters.onUpdate.forEach { it(clusters) }
        selectedChild.value = null

        children.clear()
        if (cluster != null) {
            loadChildren(cluster.cluster.id)

            MessageHub.send(cluster.cluster, ClusterAction.SHOW_START, this)
        } else {
            MessageHub.send(Cluster.empty(), ClusterAction.CLEAR_START, this)
        }
    }

    private fun onChildSelected(cluster: ClusterListItem?) {
        children.onUpdate.forEach { it(children) }

        if (cluster != null) {
            MessageHub.send(cluster.cluster, ClusterAction.SHOW_END, this)
        } else {
            MessageHub.send(Cluster.empty(), ClusterAction.CLEAR_END, this)
        }
    }
}