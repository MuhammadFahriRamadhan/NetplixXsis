plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.xsis.netplix"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.xsis.netplix"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String","API_KEY","\"2e8e301575ac531c4b5844743e854724\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true  // Enable BuildConfig feature
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
    viewBinding {
        enable =  true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.live.data)
    implementation(libs.retrofit.android)
    implementation(libs.retrofit.adapter.rx)
    implementation(libs.rx.android)
    implementation(libs.rxjava2)
    implementation(libs.retrofit2.gson)
    implementation(libs.retrofit2.jackson)
    implementation(libs.okhttp.android)
    implementation(libs.okhttp.logging)
    implementation(libs.okhttp.url)
    implementation(libs.coroutines.kotlin)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.swipe.refresh)
    implementation(libs.glide)
    implementation(libs.view.model.ktx)
    implementation(libs.circle.image)
    implementation(libs.rounded.image)
    implementation(libs.fast.adapter)
    implementation(libs.fast.adapter.binding)
    implementation(libs.fast.adapter.util)
    implementation(libs.flex.box)
    implementation(libs.youtube)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}