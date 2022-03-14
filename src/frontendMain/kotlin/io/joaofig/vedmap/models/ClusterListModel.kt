package io.joaofig.vedmap.models

import io.joaofig.vedmap.states.ClusterListState
import io.kvision.state.ObservableListWrapper
import io.kvision.state.ObservableValue

class ClusterListModel {
    private val clusterList: MutableList<ClusterListState> = mutableListOf()
    val clusters: ObservableListWrapper<ClusterListState> = ObservableListWrapper(mutableListOf())
    val sortAscending = ObservableValue<Boolean?>(null)

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
            true -> list.sortedBy { it.name }.toMutableList()
            false -> list.sortedByDescending { it.name }.toMutableList()
        }
    }

    private fun filterClusters(
        filter: String,
        list: MutableList<ClusterListState>
    ): MutableList<ClusterListState> {
        return if (filter == "") {
            list
        } else {
            list.filter { it.name.contains(filter, true) }.toMutableList()
        }
    }
}