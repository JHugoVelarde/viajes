// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // DI
    alias(libs.plugins.hilt) apply false
    // Code generation
    alias(libs.plugins.ksp) apply false
    // Serialization
    alias(libs.plugins.kotlin.serialization) apply false
    // Firebase
    // alias(libs.plugins.google.services) apply false
}