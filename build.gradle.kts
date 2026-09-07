buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        // Das Plugin-Bundle enthält nun auch das Maven-Publish-Plugin
        classpath(libs.bundles.plugins)
    }
}

plugins {
    alias(libs.plugins.plugin.kotlin) apply false
    alias(libs.plugins.plugin.android) apply false
    alias(libs.plugins.plugin.maven) apply false
    alias(libs.plugins.plugin.ktlint) apply false
    alias(libs.plugins.plugin.detekt) apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    
    plugins.withId("com.vanniktech.maven.publish") {
        extensions.configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
            sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
        }
    }
    
    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        config = files("$rootProject.projectDir/detekt.yml")
        parallel = true
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
