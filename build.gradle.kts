android {
        namespace = "com.example.livingcostapp"
        compileSdk = 34
        buildToolsVersion = "34.0.0"
        defaultConfig {
                targetSdk = 34
                minSdk = 30
        }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.jetbrains.kotlin.android)
        alias(libs.plugins.kotlin.serialization)
}
