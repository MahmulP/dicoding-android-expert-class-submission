plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.mahmulp.storyapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mahmulp.storyapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
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
    dynamicFeatures += setOf(":favorite")

    lint {
        // Menonaktikan Issue ID yang disebutkan.
        disable += "TypographyFractions" + "TypographyQuotes"
        // Mengkatifkan Issue ID yang disebutkan.
        enable += "RtlHardcoded" + "RtlCompat" + "RtlEnabled"
        // Untuk memeriksa Issue ID tertentu saja, yang lainnya akan dihiraukan
        // Ia akan menghiraukan kode enable dan disable yang sebelumnya
        checkOnly += "NewApi" + "InlinedApi"
        // Jika true, report hasil lint akan dinonaktifkan
        quiet = true
        // Jika true (default), proses build akan dihentikan jika ada eror.
        abortOnError = false
        // Jika true, hanya memberikan report eror.
        ignoreWarnings = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.feature.delivery.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.rxjava)
    implementation(libs.rxbinding)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.android.viewmodel)

    implementation(libs.glide)

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.14")
}