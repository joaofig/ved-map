package io.joaofig.vedmap.viewmodels

import io.joaofig.vedmap.messages.ClusterAction
import io.joaofig.vedmap.messages.ClusterMessage
import io.joaofig.vedmap.states.ClusterListState
import io.kvision.state.ObservableListWrapper
import io.kvision.state.ObservableValue

class ClusterListViewModel : ViewModel() {
    private val clusterList: MutableList<ClusterListState> = mutableListOf()
    val clusters: ObservableListWrapper<ClusterListState> = ObservableListWrapper(mutableListOf())
    val sortAscending = ObservableValue<Boolean?>(null)
    val showCluster = ObservableValue<ClusterListState?>(null)
    val hideCluster = ObservableValue<ClusterListState?>(null)

    init {
        sortAscending.subscribe { sortAndFilter() }
    }

    var nameFilter: String = ""
        set(filter) {
            field = filter
            sortAndFilter()
        }

    fun initialize(list: List<ClusterListState>) {
        clusterList.addAll(list)
        clusters.addAll(clusterList)
        clusterList.forEach {
            it.isSelected.subscribe { state ->
                if (state) {
                    showCluster.value = it
                    MessageHub.clusterMessenger.send(ClusterMessage(it.cluster, ClusterAction.SELECTED))
                } else {
                    hideCluster.value = it
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
        list:MutableList<ClusterListState>
    ): MutableList<ClusterListState> {
        return when (sort) {
            null -> list
            true -> list.sortedBy { it.cluster.name }.toMutableList()
            false -> list.sortedByDescending { it.cluster.name }.toMutableList()
        }
    }

    private fun filterClusters(
        filter: String,
        list: MutableList<ClusterListState>
    ): MutableList<ClusterListState> {
        return if (filter == "") {
            list
        } else {
            list.filter { it.cluster.name.contains(filter, true) }.toMutableList()
        }
    }
}