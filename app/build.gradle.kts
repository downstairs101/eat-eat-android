plugins {
    id(Plugins.androidApplication)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
    id(Plugins.Kotlin.kapt)
    id(Plugins.googleServices)
}

android {

    signingConfigs {
        create("release") {
            storeFile = Release.keystoreFile
            storePassword = Release.keystorePass
            keyAlias = Release.keyAlias
            keyPassword = Release.keyPass
        }

        create("development") {
            storeFile = Development.keystoreFile
            storePassword = Development.keystorePass
            keyAlias = Development.keyAlias
            keyPassword = Development.keyPass
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            signingConfig = signingConfigs.getByName("development")
        }

        create("local"){
            initWith(getByName("debug"))
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:80/\"")
            applicationIdSuffix = ".local"
        }

        create("staging") {
            initWith(getByName("debug"))
            buildConfigField("String", "BASE_URL", "\"https://tosplitapi.azurewebsites.net/\"")
            applicationIdSuffix = ".staging"
        }

        create("production") {
            initWith(getByName("release"))
            buildConfigField("String", "BASE_URL", "\"https://tosplitapi.azurewebsites.net/\"")
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

    implementation(Dependencies.Androidx.activity)
    implementation(Dependencies.Androidx.fragment)

    implementation(Dependencies.Androidx.navigationFragment)
    implementation(Dependencies.Androidx.navigationUI)

    implementation(Dependencies.Androidx.lifecycleLiveData)
    implementation(Dependencies.Androidx.lifecycleViewModel)
    implementation(Dependencies.Androidx.lifecycleExtensions)

    implementation("com.google.firebase:firebase-analytics:17.5.0")

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshi)

    implementation(Dependencies.dagger)
    implementation(Dependencies.daggerAndroid)
    implementation(Dependencies.daggerSupport)
    kapt(Dependencies.daggerCompiler)
    kapt(Dependencies.daggerProcessor)

    implementation(project(":core"))

    testImplementation(Dependencies.Test.jUnit)
    testImplementation(Dependencies.Test.mockitoInline)
    testImplementation(Dependencies.Test.mockitoKotlin)
    testImplementation(Dependencies.Test.assertJ)

    testImplementation(Dependencies.Test.androidxCore)
    testImplementation(Dependencies.Test.androidxJunit)

    testImplementation(Dependencies.Test.robolectric)

    testImplementation(Dependencies.Test.archCore)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Test.fragment)

    androidTestImplementation(Dependencies.Test.runner)
    androidTestImplementation(Dependencies.Test.espresso)

    kaptTest(Dependencies.daggerCompiler)
}