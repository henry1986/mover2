import org.daiv.mover.versions
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}


kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        browser {
            binaries.executable()
//            dceTask {
//                dceOptions.devMode = true
//            }

            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
    /* Targets configuration omitted.
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(versions.kutil())
                implementation(versions.eventbus())
                implementation(versions.serialization())
                implementation(versions.kotlinx_html())
//                implementation(project(":drivers:sml-isa"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(project(":moverlib"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

val assembleWeb = tasks.register("assembleWeb"){
    doLast {
        val name = "gui"
        val distributions = "distributions"
        val jsFolder = "js"
        val fjs = File("${project.buildDir}/$distributions/$name.js")
        val fjsMap = File("${project.buildDir}/$distributions/$name.js.map")
        File("${project.buildDir}/$distributions/$jsFolder/").mkdirs()
        fjs.renameTo(File("${project.buildDir}/$distributions/$jsFolder/$name.js"))
        fjsMap.renameTo(File("${project.buildDir}/$distributions/$jsFolder/$name.js.map"))
    }
//    from("${project.buildDir}/distributions/frontend.js")
//    from("${project.buildDir}/distributions/frontend.js")
//    into("${project.buildDir}/distributions/js/")
}

//tasks.getByName<KotlinWebpack>("jsBrowserProductionWebpack") {
////    outputFileName = "output.js"
//}
//
//tasks.getByName<Jar>("jvmJar") {
//    dependsOn(assembleWeb)
//    dependsOn(tasks.getByName("jsBrowserProductionWebpack"))
//    val jsBrowserProductionWebpack = tasks.getByName<KotlinWebpack>("jsBrowserProductionWebpack")
//    from(File(jsBrowserProductionWebpack.destinationDirectory, jsBrowserProductionWebpack.outputFileName))
//    from(File(jsBrowserProductionWebpack.destinationDirectory, "${jsBrowserProductionWebpack.outputFileName}.map"))
//}

