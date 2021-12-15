package io.joaofig.vedmap.databases
import io.joaofig.vedmap.models.Trip
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

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