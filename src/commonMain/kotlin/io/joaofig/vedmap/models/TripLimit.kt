package io.joaofig.vedmap.models

import kotlinx.serialization.Serializable

@Serializable
data class TripLimit (
    val id: Int = 0,
    val name: String = ""
)