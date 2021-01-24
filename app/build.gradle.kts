import org.daiv.mover.versions

plugins {
    kotlin("jvm")
    application
}


val main = "org.daiv.mover.MServerKt"

application {
    mainClassName = main
}

dependencies {
    implementation(versions.kutil())
    implementation(versions.gson())
    implementation(versions.jpersistence())
    implementation(project(":moverlib"))
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.getByName<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = main
    }
    from({
             project.configurations["runtimeClasspath"].filter { it.name.endsWith("jar") }.map { project.zipTree(it) }
         })
}


