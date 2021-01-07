import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

typealias AppPlugin = com.android.build.gradle.AppPlugin
typealias LibraryPlugin = com.android.build.gradle.LibraryPlugin
typealias AndroidExtension = com.android.build.gradle.TestedExtension

apply<LintPlugin>()

buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Path.androidGradle)
        classpath(Path.kotlinGradle)
        classpath(Path.playServices)
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
        if (this is AppPlugin || this is LibraryPlugin) {
            extensions.findByType<AndroidExtension>()?.androidConfig(name)
        }
    }

    tasks.withType<KotlinCompile>().all {
        kotlinOptions.freeCompilerArgs += "-Xallow-result-return-type"
        kotlinOptions.jvmTarget = "1.8"
    }
}


fun AndroidExtension.androidConfig(moduleName: String) {
    println("Applying common configs to android modules")

    compileSdkVersion(29)
    buildToolsVersion("30.0.3")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)

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

    variantFilter {
        if (buildType.name.contains("release") || buildType.name.contains("debug")) {
            ignore = true
        }
    }

    lintOptions {
        xmlOutput = File("$rootDir/reports/${moduleName}-lint.xml")
        htmlOutput = File("$rootDir/reports/${moduleName}-lint.html")
        isAbortOnError = false
    }
}

tasks.register("clean") {
    delete(project.buildDir)
}