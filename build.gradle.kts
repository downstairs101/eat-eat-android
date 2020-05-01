import Build_gradle.AndroidBasePlugin
import com.android.build.gradle.TestedExtension

typealias AndroidBasePlugin = com.android.build.gradle.BasePlugin

buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.6.3")
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
            project.extensions
                .findByType<TestedExtension>()
                ?.applyCommonConfigs()
        }
    }
}


fun TestedExtension.applyCommonConfigs() {
    println("Applying common configs to android modules")

    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        minSdkVersion(23)
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
