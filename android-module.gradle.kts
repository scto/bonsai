plugins {
    alias(libs.plugins.plugin.kotlin) // Entspricht kotlin-android / kotlin-jvm
    alias(libs.plugins.plugin.android) // Entspricht com.android.library oder com.android.application
    //alias(libs.plugins.plugin.maven)
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

kotlin {
    // Falls Kotlin Multiplatform genutzt wird, hier die SourceSets definieren
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.compose.runtime)
                implementation(libs.compose.material)
            }
        }
    }
}
