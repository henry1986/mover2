plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://repo.gradle.org/gradle/libs-releases") }
    maven { url = uri("https://artifactory.daiv.org/artifactory/gradle-dev-local") }
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("org.daiv.dependency:DependencyHandling:0.0.108")
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
}
