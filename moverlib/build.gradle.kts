import org.daiv.mover.versions

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

group = "org.daiv.mover"
version = "1.0-SNAPSHOT"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(LEGACY) {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }

    
    sourceSets {
        val commonMain by getting{
            dependencies{
                implementation(versions.kutil())
                implementation(versions.eventbus())
                implementation(versions.serialization())
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting{
            dependencies{
                implementation(versions.ktor("gson"))
                implementation(versions.ktor("websockets"))
                implementation(versions.ktor("server-netty"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting{
            dependencies{
                implementation(versions.kotlinx_html())
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}
