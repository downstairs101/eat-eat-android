plugins {
    id(Plugins.androidLibrary)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
    id(Plugins.Kotlin.kapt)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create("local") { initWith(getByName("debug")) }

        create("staging") { initWith(getByName("release")) }

        create("production") { initWith(getByName("release")) }
    }
}

dependencies {

    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Androidx.core)
    implementation(Dependencies.Androidx.appCompat)
    implementation(Dependencies.Androidx.constraintLayout)
    implementation(Dependencies.materialDesign)
    implementation(project(":core"))

    implementation("androidx.media:media:1.2.1")
    implementation("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")

    implementation(Dependencies.dagger)
    implementation(Dependencies.daggerAndroid)
    implementation(Dependencies.daggerSupport)
    kapt(Dependencies.daggerCompiler)
    kapt(Dependencies.daggerProcessor)

    implementation(ExoPlayer.core)
    implementation(ExoPlayer.ui)
    implementation(ExoPlayer.hls)
    implementation(ExoPlayer.okhttp)
    implementation(ExoPlayer.mediaSession)
    implementation(ExoPlayer.cast)


    testImplementation(Dependencies.Test.jUnit)
    testImplementation(Dependencies.Test.androidxJunit)
    androidTestImplementation(Dependencies.Test.espresso)
}