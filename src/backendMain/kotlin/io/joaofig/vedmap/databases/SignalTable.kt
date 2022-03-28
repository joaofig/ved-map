package io.joaofig.vedmap.databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object SignalTable :  IntIdTable(name = "signal", columnName = "signal_id") {
    val dayNumber: Column<Double> = double("day_num")
    val vehicleId:  Column<Int> = integer("vehicle_id")
    val tripId: Column<Int> = integer("trip_id")
    val timeStamp: Column<Long> = long("time_stamp")
    val latitude: Column<Double> = double("latitude")
    val longitude: Column<Double> = double("longitude")
    val speed: Column<Double> = double("speed")
    val rpm: Column<Double> = double("rpm")
}

class SignalRow(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<SignalRow>(SignalTable)

    var dayNumber by SignalTable.dayNumber
    var vehicleId by SignalTable.vehicleId
    var tripId by SignalTable.tripId
    var timeStamp by SignalTable.timeStamp
    var latitude by SignalTable.latitude
    var longitude by SignalTable.longitude
    var speed by SignalTable.speed
    var rpm by SignalTable.rpm
}