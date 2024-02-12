plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

apply(from = "../shared_dependencies.gradle")

android {
    namespace = "com.example.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    tasks.named(":favorite:exportReleaseConsumerProguardFiles").configure { 
        dependsOn(":favorite:extractProguardFiles") 
    }

    tasks.named(":favorite:exportReleaseConsumerProguardFiles").configure { 
        mustRunAfter(":favorite:extractProguardFiles") 
    }

    buildTypes {

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.8.20")
    implementation(project(":app"))
    implementation(project(":core"))
}
