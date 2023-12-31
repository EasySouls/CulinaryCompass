plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id ("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "dev.easysouls.culinarycompass"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.easysouls.culinarycompass"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
    sourceSets {
        // Adds exported schema location as test app assets.
        getByName("androidTest").assets.srcDir("$projectDir/schemas")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended-android:1.5.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.27.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // AndroidJUnitRunner and JUnit Rules
    androidTestImplementation ("androidx.test:runner:1.6.0-alpha04")
    androidTestImplementation ("androidx.test:rules:1.6.0-alpha01")

    // Google Truth
    testImplementation ("com.google.truth:truth:1.1.5")
    androidTestImplementation ("com.google.truth:truth:1.1.5")

    // Navigation
    implementation ("androidx.navigation:navigation-compose:2.7.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    // Room
    val roomVersion = "2.6.0-beta01"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation ("androidx.room:room-migration:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    testImplementation ("androidx.room:room-testing:$roomVersion")
    androidTestImplementation ("androidx.room:room-testing:$roomVersion")

    // Paging 3
    implementation("androidx.paging:paging-compose:3.2.0")
    implementation("androidx.paging:paging-runtime-ktx:3.2.0")
    implementation ("androidx.room:room-paging:$roomVersion")

    // Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:$roomVersion")

    // Coil
    implementation ("io.coil-kt:coil-compose:2.4.0")

    // Gson
    implementation ("com.google.code.gson:gson:2.10.1")

    // Timber
    implementation ("com.jakewharton.timber:timber:5.0.1")

    // Google Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.2.2"))
    implementation ("com.google.firebase:firebase-analytics-ktx")
    implementation ("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation ("com.google.android.gms:play-services-auth:20.6.0")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt ("com.google.dagger:hilt-compiler:2.47")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("androidx.hilt:hilt-work:1.0.0")

    kaptTest ("com.google.dagger:hilt-android-compiler:2.47")
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.47")

    // Kotlin Symbol Processing
    implementation ("com.google.devtools.ksp:symbol-processing-api:1.9.0-1.0.11")

    // WorkManager
    implementation ("androidx.work:work-runtime-ktx:2.8.1")
    testImplementation ("androidx.work:work-testing:2.8.1")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}