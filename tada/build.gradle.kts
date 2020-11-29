plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt") // Enabling annotation processing for Kotlin source code. See https://kotlinlang.org/docs/reference/kapt.html
}

val kotlinVersion = "1.4.20" // releases: Kotlin doesn't supply a browseable releases page.
val daggerVersion = "2.30.1" // releases: https://github.com/google/dagger/releases

val androidXAppCompatVersion = "1.2.0" //releases: https://developer.android.com/jetpack/androidx/releases/appcompat
val androidXAnnotationVersion = "1.1.0" // releases: https://developer.android.com/jetpack/androidx/releases/annotation
val androidXConstraintLayoutVersion = "2.0.4" // releases: https://developer.android.com/jetpack/androidx/releases/constraintlayout

val androidXTestEspressoVersion = "3.3.0" // releases: https://developer.android.com/jetpack/androidx/releases/test
val androidXTestRulesVersion = "1.3.0" // releases: https://developer.android.com/jetpack/androidx/releases/test
val androidXTestJunitVersion = "1.1.2" // releases: https://developer.android.com/jetpack/androidx/releases/test

android {
    compileSdkVersion(28)
    buildToolsVersion("29.0.2")

    lintOptions {
        isAbortOnError = false
    }

    defaultConfig {
        applicationId = "us.mn.dgtc.tada"
        minSdkVersion(25)
        targetSdkVersion(26)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "us.mn.dgtc.tada.CustomRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    implementation("com.google.dagger:dagger:$daggerVersion")
    implementation("com.google.dagger:dagger-android-support:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    implementation("androidx.appcompat:appcompat:$androidXAppCompatVersion")
    implementation("androidx.constraintlayout:constraintlayout:$androidXConstraintLayoutVersion")

    testImplementation("com.squareup.assertj:assertj-android:1.1.1")
    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-all:1.10.19")

    androidTestImplementation("androidx.test.ext:junit:$androidXTestJunitVersion")
    androidTestImplementation("androidx.test:rules:$androidXTestRulesVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$androidXTestEspressoVersion")
    androidTestImplementation("androidx.annotation:annotation:$androidXAnnotationVersion")
}
