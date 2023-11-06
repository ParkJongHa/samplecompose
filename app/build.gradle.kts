plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // default
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.compose.ui:ui-unit-android:1.5.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // ex001_gif
    implementation("io.coil-kt:coil:2.2.2")
    implementation("io.coil-kt:coil-gif:2.4.0")
    implementation("io.coil-kt:coil-compose:2.2.2")

    // ex003_youtube_player
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")

    // ex004_exoplayer
    val media3Version by extra { "1.1.1" }
    implementation("androidx.media3:media3-exoplayer:$media3Version") // For media playback using ExoPlayer
    implementation("androidx.media3:media3-exoplayer-dash:$media3Version") // For DASH playback support with ExoPlayer
    implementation("androidx.media3:media3-exoplayer-hls:$media3Version") // For HLS playback support with ExoPlayer
    implementation("androidx.media3:media3-exoplayer-rtsp:$media3Version") // For RTSP playback support with ExoPlayer
    implementation("androidx.media3:media3-exoplayer-ima:$media3Version") // For ad insertion using the Interactive Media Ads SDK with ExoPlayer
    implementation("androidx.media3:media3-datasource-cronet:$media3Version") // For loading data using the Cronet network stack
    implementation("androidx.media3:media3-datasource-okhttp:$media3Version") // For loading data using the OkHttp network stack
    implementation("androidx.media3:media3-datasource-rtmp:$media3Version") // For loading data using librtmp
    implementation("androidx.media3:media3-ui:$media3Version") // For building media playback UIs
    implementation("androidx.media3:media3-ui-leanback:$media3Version") // For building media playback UIs for Android TV using the Jetpack Leanback library
    implementation("androidx.media3:media3-session:$media3Version") // For exposing and controlling media sessions
    implementation("androidx.media3:media3-extractor:$media3Version") // For extracting data from media containers
    implementation("androidx.media3:media3-cast:$media3Version") // For integrating with Cast
    implementation("androidx.media3:media3-transformer:$media3Version") // For transforming media files
    implementation("androidx.media3:media3-test-utils:$media3Version") // Utilities for testing media components (including ExoPlayer components)
    implementation("androidx.media3:media3-database:$media3Version") // Common functionality for media database components
    implementation("androidx.media3:media3-decoder:$media3Version") // Common functionality for media decoders
    implementation("androidx.media3:media3-datasource:$media3Version") // Common functionality for loading data
    implementation("androidx.media3:media3-common:$media3Version") // Common functionality used across multiple media libraries
    implementation("androidx.media3:media3-exoplayer-workmanager:$media3Version") // For scheduling background operations using Jetpack Work's WorkManager with ExoPlayer
    implementation("androidx.media3:media3-test-utils-robolectric:$media3Version") // Utilities for testing media components (including ExoPlayer components) via Robolectric

    implementation("io.ktor:ktor-client-core:2.3.5")
    implementation("io.ktor:ktor-client-cio:2.3.5")

    implementation("com.google.code.gson:gson:2.10.1")
}