import com.android.build.gradle.TestedExtension

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
    plugins.withId(Plugins.androidApplication) {
        project.extensions
            .findByType<TestedExtension>()
            ?.applyCommonConfigs()
    }

    plugins.withId(Plugins.androidLibrary) {
        project.extensions
            .findByType<TestedExtension>()
            ?.applyCommonConfigs()
    }
}


fun TestedExtension.applyCommonConfigs() {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(29)
        println("configurando o projeto")
        println(project.projectDir.name)
    }
}
