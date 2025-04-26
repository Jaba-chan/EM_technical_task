plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    api(project(":core:common"))
    api(project(":core:model"))
    implementation(libs.kotlinx.coroutines.android)
}