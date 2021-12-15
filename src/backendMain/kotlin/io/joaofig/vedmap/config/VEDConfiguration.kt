package io.joaofig.vedmap.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class VEDConfiguration {
    @Value("\${VED_DB_DRIVER}")
    val databaseDriver: String = "org.sqlite.JDBC"

    @Value("\${VED_DB_URL}")
    val databaseUrl: String = "jdbc:sqlite:./data/ved.db"
}