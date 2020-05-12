import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

typealias AndroidBasePlugin = com.android.build.gradle.BasePlugin
typealias AndroidExtension = com.android.build.gradle.TestedExtension

buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0-rc01")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {
    plugins.whenPluginAdded {
        if (this is AndroidBasePlugin) {
            extensions.findByType<AndroidExtension>()?.applyCommonConfigs()
        }
    }

    tasks.withType<KotlinCompile>().all {
        kotlinOptions.freeCompilerArgs += "-Xallow-result-return-type"
        kotlinOptions.jvmTarget = "1.8"
    }
}


fun AndroidExtension.applyCommonConfigs() {
    println("Applying common configs to android modules")

    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}