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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    implementation(Dependencies.Androidx.core)
    implementation(Dependencies.Androidx.appCompat)
    implementation(Dependencies.materialDesign)
    implementation(Dependencies.Androidx.constraintLayout)

    implementation(Dependencies.Androidx.navigationUI)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshi)
    implementation(Dependencies.httpLogging)

    implementation(Dependencies.dagger)
    implementation(Dependencies.daggerAndroid)
    implementation(Dependencies.daggerSupport)
    kapt(Dependencies.daggerCompiler)
    kapt(Dependencies.daggerProcessor)

    testImplementation(Dependencies.Test.jUnit)
    testImplementation(Dependencies.Test.mockito)
    testImplementation(Dependencies.Test.mockitoKotlin)
    testImplementation(Dependencies.Test.assertJ)

    testImplementation(Dependencies.Test.androidxCore)
    testImplementation(Dependencies.Test.androidxJunit)

    testImplementation(Dependencies.Test.robolectric)

    testImplementation(Dependencies.Test.archCore)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Test.fragment)

    kaptTest(Dependencies.daggerCompiler)
}