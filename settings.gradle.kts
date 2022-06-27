pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }
    resolutionStrategy {
        eachPlugin {
            when {
                requested.id.id == "org.springframework.boot" -> useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
            }
            if (requested.id.id == "kotlin2js") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
        }
    }
}
rootProject.name = "vedmap"
