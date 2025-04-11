plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.example.firebase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.firebase"
        minSdk = 24
        targetSdk = 34
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

    implementation (libs.play.services.auth)
    implementation (libs.firebase.auth)
    implementation (libs.firebase.database)
    implementation (platform(libs.firebase.bom))
    implementation (libs.firebase.database.ktx)
    implementation (libs.firebase.firestore)
    implementation (libs.firebase.storage)
    implementation (libs.firebase.ui.database)


    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.gson)



    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}