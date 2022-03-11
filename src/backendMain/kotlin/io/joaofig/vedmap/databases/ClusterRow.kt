package io.joaofig.vedmap.databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ClusterRow(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ClusterRow>(ClusterTable)

    var name by ClusterTable.name
}