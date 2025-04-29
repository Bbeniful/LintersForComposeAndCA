plugins {
    id("java")
    kotlin("jvm")
}



dependencies {
    implementation(libs.lint.api)
    implementation(libs.lint.checks)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}