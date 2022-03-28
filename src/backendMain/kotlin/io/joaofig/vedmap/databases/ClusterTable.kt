package io.joaofig.vedmap.databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object ClusterTable : IntIdTable(name = "cluster", columnName = "cluster_id") {
    val name: Column<String> = text("cluster_name")
}

class ClusterRow(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ClusterRow>(ClusterTable)

    var name by ClusterTable.name
}