package io.joaofig.vedmap.repositories

import com.uber.h3core.H3Core
import io.joaofig.vedmap.databases.*
import io.joaofig.vedmap.models.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class ClusterRepository {
    private val db = VehicleEnergyDatabase.db

    fun getMapBoundaries(): GeoBounds {
        return transaction(db = db) {
            val minLat = ClusterPointRow.all().minOf { it.latitude }
            val maxLat = ClusterPointRow.all().maxOf { it.latitude }
            val minLon = ClusterPointRow.all().minOf { it.longitude }
            val maxLon = ClusterPointRow.all().maxOf { it.longitude }

            GeoBounds(minLat, maxLat, minLon, maxLon)
        }
    }

    fun getClusters(): List<Cluster> {
        return transaction(db = db) {
            ClusterRow.all().map {
                Cluster(it.id.value, it.name)
            }
        }
    }

    fun getClusterPolygon(clusterId: Int): GeoMultiPolygon {
        val h3 = H3Core.newInstance()
        val hexagons = transaction(db = db) {
            ClusterPointRow.find {
                ClusterPointTable.clusterId eq clusterId
            }.map {
                it.h3
            }.distinct()
        }

        val multiList = h3.h3AddressSetToMultiPolygon(hexagons, true)
        val geoMultiPolygon = GeoMultiPolygon(
            multiList.map { polygon ->
                GeoPolygon(
                    polygon.map { ring ->
                        GeoPolyline(
                            ring.map { l ->
                                GeoLocation(l.lat, l.lng)
                            }
                        )
                    }
                )
            }
        )
        return geoMultiPolygon
    }

    fun getOutboundClusters(clusterId: Int): List<Int> {
        return transaction(db = db) {
            TripRow.find {
                TripTable.clusterIni eq clusterId
            }.map {
                it.clusterEnd
            }.distinct()
        }
    }

    fun getInboundClusters(clusterId: Int): List<Int> {
        return transaction(db = db) {
            TripRow.find {
                TripTable.clusterEnd eq clusterId
            }.map {
                it.clusterIni
            }.distinct()
        }
    }

    fun getClusterPoints(clusterId: Int): List<GeoLocation> {
        return transaction(db = db) {
            ClusterPointRow.find {
                ClusterPointTable.clusterId eq clusterId
            }.map {
                GeoLocation(it.latitude, it.longitude)
            }
        }
    }
}