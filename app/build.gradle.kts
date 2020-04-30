plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")

}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.downstairs"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            buildConfigField("String", "BASE_URL", "http://localhost.com")
        }

        getByName("debug") {
            buildConfigField("String", "BASE_URL", "http://localhost.com")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}



dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72")

    implementation("androidx.core:core-ktx:1.2.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("com.google.android.material:material:1.1.0")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta4")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")

    implementation("androidx.activity:activity-ktx:1.1.0")
    implementation("androidx.fragment:fragment-ktx:1.2.4")

    implementation("androidx.navigation:navigation-fragment-ktx:2.2.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.2")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.3.0-alpha05")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    implementation("androidx.room:room-runtime:2.2.3")
    kapt("androidx.room:room-compiler:2.2.3")

    implementation("com.squareup.retrofit2:retrofit:2.6.1")
    implementation("com.squareup.retrofit2:converter-moshi:2.6.1")

    implementation("com.google.dagger:dagger:2.25.3")
    implementation("com.google.dagger:dagger-android:2.25.3")
    implementation("com.google.dagger:dagger-android-support:2.25.3")
    kapt("com.google.dagger:dagger-compiler:2.25.3")
    kapt("com.google.dagger:dagger-android-processor:2.25.3")
    kaptTest("com.google.dagger:dagger-compiler:2.25.3")

    implementation("androidx.paging:paging-runtime-ktx:2.1.1")
    testImplementation("androidx.paging:paging-common-ktx:2.1.1")

    testImplementation("androidx.test:core-ktx:1.2.0")
    testImplementation("androidx.test.ext:junit-ktx:1.1.1")

    testImplementation("junit:junit:4.13")
    testImplementation("org.mockito:mockito-inline:2.28.2")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.robolectric:robolectric:4.3")
    testImplementation("org.assertj:assertj-core:3.13.2")

    testImplementation("android.arch.core:core-testing:1.1.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.3")
    testImplementation("androidx.fragment:fragment-testing:1.2.4")

    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    implementation(project(":core"))
}
