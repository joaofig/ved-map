package io.joaofig.vedmap.databases

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object MapPointTable: IntIdTable(name = "signal", columnName = "signal_id") {
    val dayNumber:      Column<Double> = double("day_num")
    val vehicleId:      Column<Int> = integer("vehicle_id")
    val tripId:         Column<Int> = integer("trip_id")
    val timestamp:      Column<Int> = integer("timestamp")
    val latitude:       Column<Double> = double("latitude")
    val longitude:      Column<Double> = double("longitude")
    val speed:          Column<Double?> = double("speed").nullable()
    val maf:            Column<Double?> = double("maf").nullable()
    val rpm:            Column<Double?> = double("rpm").nullable()
    val absoluteLoad:   Column<Double?> = double("abs_load").nullable()
}