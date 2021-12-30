package io.joaofig.vedmap.models

data class VehicleModel(
    val vehicles: List<Vehicle> = listOf(),
    val typeFilter: String? = null,
    val engineFilter: String? = null
)