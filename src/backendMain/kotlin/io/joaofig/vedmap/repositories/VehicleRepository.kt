package io.joaofig.vedmap.repositories

import io.joaofig.vedmap.databases.VehicleEnergyDatabase
import io.joaofig.vedmap.databases.VehicleRow
import io.joaofig.vedmap.models.Vehicle
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class VehicleRepository {
    private val db = VehicleEnergyDatabase.db

    fun getVehicles(): List<Vehicle> {
        return transaction(db = db) {
            VehicleRow.all().map { it.toVehicle() }
        }
    }
}