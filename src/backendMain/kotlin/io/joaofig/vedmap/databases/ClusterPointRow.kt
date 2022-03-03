package io.joaofig.vedmap.databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ClusterPointRow(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ClusterPointRow>(ClusterPointTable)

    var clusterId by ClusterPointTable.clusterId
    var latitude by ClusterPointTable.latitude
    var longitude by ClusterPointTable.longitude
    var h3 by ClusterPointTable.h3
}