import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    id("org.jetbrains.compose") version "0.4.0"
}

group = "me.farmen"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(compose.desktop.currentOs)

    testImplementation("org.assertj:assertj-core:3.22.0")

    implementation("org.slf4j:slf4j-simple:2.0.0-alpha7")

    implementation("org.eclipse.milo:sdk-core:0.6.4")
    implementation("org.eclipse.milo:sdk-client:0.6.4")
    implementation("org.eclipse.milo:sdk-server:0.6.4")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "agiluses-mock-controller"
            packageVersion = "1.0.0"
        }
    }
}