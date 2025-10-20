plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.pokeapi"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.pokeapi"
        minSdk = 30
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("androidx.appcompat:appcompat:1.7.0");
    implementation ("com.google.android.material:material:1.13.0");
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4");

    implementation ("com.squareup.retrofit2:retrofit:2.11.0");
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0");
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.14");

    implementation ("com.github.bumptech.glide:glide:4.16.0");
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0");
    }