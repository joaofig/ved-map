package io.joaofig.vedmap.databases

import io.joaofig.vedmap.models.Trip
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
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

class TripRow(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<TripRow>(TripTable)

    var vehicleId       by TripTable.vehicleId
    var dayNumber       by TripTable.dayNumber
    var tsIni           by TripTable.tsIni
    var tsEnd           by TripTable.tsEnd
    var clusterIni      by TripTable.clusterIni
    var clusterEnd      by TripTable.clusterEnd

    fun toTrip(): Trip {
        return Trip(
            id          = id.value,
            vehicleId   = vehicleId,
            dayNumber   = dayNumber,
            tsIni       = tsIni,
            tsEnd       = tsEnd,
            clusterIni  = clusterIni,
            clusterEnd  = clusterEnd
        )
    }
}
