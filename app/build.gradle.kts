plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("com.google.devtools.ksp")

//    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.paymentapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.paymentapplication"
        minSdk = 24
        targetSdk = 33
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


//        debug {
//            isMinifyEnabled = true
//            isShrinkResources = true
//            useProguard true
//            isDebuggable = false
//            proguardFiles ( getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
//        }
//
//        release {
//            isMinifyEnabled = true   // Obfuscate and minify codes
//            isShrinkResources = true // Remove unused resources
//           isUseProguard = true     // Remove unused codes
//            isDebuggable = false
//            proguardFiles ( getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
//        }


    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    // Allow references to generated code
//    kapt {
//        correctErrorTypes = true
//    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //viewPager2 library
    implementation("androidx.viewpager2:viewpager2:1.1.0")

    //stepper library
    implementation ("com.tbuonomo:dotsindicator:5.0")

    // design dynamic
    implementation ("com.intuit.ssp:ssp-android:1.0.6")
    implementation ("com.intuit.sdp:sdp-android:1.0.6")

    // lottie animation
    implementation ("com.airbnb.android:lottie:4.1.0")

    implementation ("com.github.bumptech.glide:glide:4.13.0")

//    Hilt-Dagger
    val hilt_version="2.51.1"
    implementation ("com.google.dagger:hilt-android:$hilt_version")
    ksp ("com.google.dagger:hilt-compiler:$hilt_version")

//    Lifecycles
    val lifecycle_version = "2.8.2" //2.8.1
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

//    Room Database
    val room_version = "2.6.1"
    implementation ("androidx.room:room-runtime:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")
    ksp ("androidx.room:room-compiler:$room_version")

//    Coroutines
    val coroutines_version = "1.7.3"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

//    Navigation
    val navigation_version = "2.7.7"
    implementation ("androidx.navigation:navigation-fragment-ktx:$navigation_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$navigation_version")

    // Retrofit
    val retrofit_version = "2.6.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")


    // Image Loading Library
    val picasso_version = "2.71828"
    val glide_version = "4.13.0"
    val okhttp_version = "2.7.5"
    implementation ("com.squareup.picasso:picasso:$picasso_version")
    implementation ("com.squareup.okhttp:okhttp:$okhttp_version")
    implementation ("com.github.bumptech.glide:glide:$glide_version")
    ksp ("com.github.bumptech.glide:compiler:$glide_version")


//    Document Sacnner
    implementation ("com.google.android.gms:play-services-mlkit-document-scanner:16.0.0-beta1")

//    Text recognition
    implementation ("com.google.android.gms:play-services-mlkit-text-recognition:19.0.0")

}