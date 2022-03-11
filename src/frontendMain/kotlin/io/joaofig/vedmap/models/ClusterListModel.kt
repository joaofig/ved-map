package io.joaofig.vedmap.models

import io.joaofig.vedmap.binders.ClusterListBind
import io.kvision.state.ObservableListWrapper

class ClusterListModel {
    private val clusterList: MutableList<ClusterListBind> = mutableListOf()
    val clusters: ObservableListWrapper<ClusterListBind> = ObservableListWrapper(mutableListOf())

    var nameFilter: String = ""
        set(filter) {
//            console.log(filter)
            clusters.clear()
            if (filter == "") {
                clusters.addAll(clusterList)
            } else {
                clusters.addAll(clusterList.filter { it.name.contains(filter, true) } )
            }
            field = filter
        }

    fun initialize(list: List<ClusterListBind>) {
        clusterList.addAll(list)
        clusters.addAll(clusterList)
    }
}