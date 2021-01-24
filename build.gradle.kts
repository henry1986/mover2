
plugins {
    kotlin("multiplatform") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.0"
}


allprojects {
    group = "org.daiv.mover"
    version = "1.0-SNAPSHOT"
    repositories {
        mavenCentral()
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlinx")
        maven("https://artifactory.daiv.org/artifactory/gradle-dev-local")
    }
}


kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
}
