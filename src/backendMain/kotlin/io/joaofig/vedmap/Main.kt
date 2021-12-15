package io.joaofig.vedmap

import io.joaofig.vedmap.services.MapPointServiceManager
import io.joaofig.vedmap.services.TripServiceManager
import io.joaofig.vedmap.services.VehicleServiceManager
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableAutoConfiguration(
    exclude = [
        org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration::class,
        org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration::class
    ]
)
class KVApplication {
    @Bean
    fun getManagers() = listOf(
        PingServiceManager,
        TripServiceManager,
        VehicleServiceManager,
        MapPointServiceManager
    )
}

fun main(args: Array<String>) {
    runApplication<KVApplication>(*args)
}
