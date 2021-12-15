package io.joaofig.vedmap.databases

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object TripTable :  IntIdTable(name = "move", columnName = "move_id") {
    val vehicleId:  Column<Int> = integer("vehicle_id")
    val dayNumber:  Column<Double> = double("day_num")
    val tsIni:      Column<Int> = integer("ts_ini")
    val tsEnd:      Column<Int> = integer("ts_end")
    val clusterIni: Column<Int> = integer("cluster_ini")
    val clusterEnd: Column<Int> = integer("cluster_end")
}