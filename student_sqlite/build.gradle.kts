// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.5.0") // Gradle plugin for Android
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3") // Safe Args plugin
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}