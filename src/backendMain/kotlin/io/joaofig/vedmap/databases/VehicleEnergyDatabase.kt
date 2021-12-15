package io.joaofig.vedmap.databases

import io.joaofig.vedmap.config.VEDConfiguration
import org.jetbrains.exposed.sql.Database

object VehicleEnergyDatabase {
    private val config = VEDConfiguration()

    val db by lazy {
        Database.connect(config.databaseUrl, config.databaseDriver)
    }
}