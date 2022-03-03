package io.joaofig.vedmap.databases

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object ClusterPointTable : IntIdTable(name = "cluster_point", columnName = "pt_id") {
    val clusterId:  Column<Int> = integer("cluster_id")
    val latitude:   Column<Double> = double("latitude")
    val longitude:  Column<Double> = double("longitude")
    val h3:         Column<String> = text("h3")
}