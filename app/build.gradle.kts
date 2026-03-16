import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    // alias(libs.plugins.google.services)
}

// Leemos el archivo local.properties
val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}
// Extraemos la clave, o usamos un string vacío si no existe
val mapsApiKey: String = localProperties.getProperty("MAPS_API_KEY") ?: ""

android {
    namespace = "com.example.viajes"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.viajes"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

dependencies {
    // --- Compose core ---
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    // --- ViewModel ---
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    // --- Navigation ---
    implementation(libs.navigation.compose)
    // --- Hilt ---
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    // --- Room ---
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    // --- Serialization ---
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    // --- Networking ---
    implementation(libs.retrofit)
    implementation(libs.serialization.converter)
    implementation(libs.okhttp)
    // --- Images ---
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    // --- Preferences ---
    implementation(libs.datastore.preferences)
    // --- Permissions ---
    implementation(libs.accompanist.permissions)
    // --- Icons / fonts ---
    implementation(libs.material.icons.extended)
    implementation(libs.google.fonts)
    // --- Maps / location ---
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    // --- Splash ---
    implementation(libs.androidx.splashscreen)
    // --- Firebase ---
    // implementation(platform(libs.firebase.bom))
    // implementation("com.google.firebase:firebase-analytics")
    // --- Credentials ---
    // implementation(libs.androidx.credentials)
    // implementation(libs.google.id)
    // --- Testing ---
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}