package io.joaofig.vedmap.viewmodels

import io.joaofig.vedmap.messages.ClusterAction
import io.joaofig.vedmap.messages.ClusterMessage
import io.kvision.state.ObservableListWrapper
import io.kvision.state.ObservableValue

class ClusterListViewModel : ViewModel() {
    private val clusterList: MutableList<ClusterListItem> = mutableListOf()
    val clusters: ObservableListWrapper<ClusterListItem> = ObservableListWrapper(mutableListOf())
    val sortAscending = ObservableValue<Boolean?>(null)

    init {
        sortAscending.subscribe { sortAndFilter() }
    }

    var nameFilter: String = ""
        set(filter) {
            field = filter
            sortAndFilter()
        }

    fun initialize(list: List<ClusterListItem>) {
        clusterList.addAll(list)
        clusters.addAll(clusterList)
        clusterList.forEach {
            it.isSelected.subscribe { state ->
                if (state) {
                    MessageHub.clusterMessenger.send(ClusterMessage(it.cluster, ClusterAction.SELECTED))
                } else {
                    MessageHub.clusterMessenger.send(ClusterMessage(it.cluster, ClusterAction.DESELECTED))
                }
            }
        }
    }

    private fun sortAndFilter() {
        clusters.clear()
        clusters.addAll(sortClustersByName(sortAscending.value, filterClusters(nameFilter, clusterList)))
    }

    private fun sortClustersByName(
        sort: Boolean?,
        list:MutableList<ClusterListItem>
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
}