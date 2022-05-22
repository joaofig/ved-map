package io.joaofig.vedmap.repositories

import io.joaofig.vedmap.databases.*
import io.joaofig.vedmap.models.Trajectory
import io.joaofig.vedmap.models.Trip
import org.jetbrains.exposed.sql.and
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

    fun getClusterTrips(clusterIni: Int, clusterEnd: Int): List<Trip> {
        return transaction(db = db) {
            TripRow
                .find { (TripTable.clusterIni eq clusterIni) and (TripTable.clusterEnd eq clusterEnd) }
                .map { it.toTrip() }
        }
    }

    fun getSingleVehicleTrajectory(tripId: Int): Trajectory {
        return transaction(db = db) {
            val trip = TripRow
                .find { TripTable.id eq tripId }
                .map { it.toTrip() }
                .first()

            Trajectory(
                tripId,
                MapPointRow
                    .find { (MapPointTable.vehicleId eq trip.vehicleId) and
                            (MapPointTable.dayNumber eq trip.dayNumber) and
                            (MapPointTable.timestamp lessEq trip.tsEnd) }
                    .map { it.toMapPoint() }
            )
        }
    }
}