plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.altamirano.myfirstapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.altamirano.myfirstapp"
        minSdk = 29
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

buildFeatures{
    viewBinding = true
}
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

//Caracteristicas del ViewBinding

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.datastore.core.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //retrofit

    implementation(libs.glide)

    implementation(libs.bundles.retrofit)
    implementation(libs.coil)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //Recycler View
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")

    //swipeRefresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //lottie
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //Carousel
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")

    //Room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
}
