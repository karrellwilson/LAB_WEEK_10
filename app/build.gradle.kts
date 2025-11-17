plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // TAMBAHKAN KAPT DI SINI
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.lab_week_10"
    // SAYA PERBAIKI INI (sebelumnya 'release(36)')
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.lab_week_10"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // SAYA UBAH INI AGAR MENGGUNAKAN 'libs'
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)

    // --- DEPENDENSI ROOM DITAMBAHKAN DI SINI ---
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    // ----------------------------------------

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}