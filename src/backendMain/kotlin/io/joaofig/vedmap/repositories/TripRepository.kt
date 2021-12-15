package io.joaofig.vedmap.repositories

import io.joaofig.vedmap.databases.TripRow
import io.joaofig.vedmap.databases.TripTable
import io.joaofig.vedmap.databases.VehicleEnergyDatabase
import io.joaofig.vedmap.models.Trip
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class TripRepository {
    private val db = VehicleEnergyDatabase.db

    fun getTripList(vehicleId: Int): List<Int> {
        return transaction(db = db) {
            TripRow
                .find { TripTable.vehicleId eq vehicleId }
                .map { it.toTrip().id }
        }
    }

    fun getVehicleTrips(vehicleId: Int): List<Trip> {
        return transaction(db = db) {
            TripRow
                .find { TripTable.vehicleId eq vehicleId }
                .map { it.toTrip() }
        }
    }
}