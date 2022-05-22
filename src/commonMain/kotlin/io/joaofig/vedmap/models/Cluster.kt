package io.joaofig.vedmap.models

import kotlinx.serialization.Serializable

@Serializable
data class Cluster (
    val id: Int = 0,
    val name: String = ""
) {
    companion object {
        fun empty() = Cluster()
    }
}