package io.joaofig.vedmap.databases

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object ClusterTable : IntIdTable(name = "cluster", columnName = "cluster_id") {
    val name: Column<String> = text("cluster_name")
}
