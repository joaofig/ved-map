package io.joaofig.vedmap.databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object ClusterPointTable : IntIdTable(name = "cluster_point", columnName = "pt_id") {
    val clusterId:  Column<Int> = integer("cluster_id")
    val latitude:   Column<Double> = double("latitude")
    val longitude:  Column<Double> = double("longitude")
    val h3:         Column<String> = text("h3")
}

class ClusterPointRow(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ClusterPointRow>(ClusterPointTable)

    var clusterId by ClusterPointTable.clusterId
    var latitude by ClusterPointTable.latitude
    var longitude by ClusterPointTable.longitude
    var h3 by ClusterPointTable.h3
}