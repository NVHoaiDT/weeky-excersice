plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.upload_image"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.upload_image"
        minSdk = 24
        targetSdk = 35
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
}

dependencies {

    // Network & Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    //Gson
    implementation (libs.gson)

    //load ảnh với Glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}