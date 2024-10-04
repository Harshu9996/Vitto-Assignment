plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.example.vittoassignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vittoassignment"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)

    //Coroutines
    implementation(libs.kotlin.coroutines)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)

    //Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Viewmodel lifecycle
    implementation(libs.viewmodel.lifecycle)

    //Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)


    //Glide
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)

}