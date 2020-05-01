import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

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
            .findByType<BaseAppModuleExtension>()
            ?.applyCommonConfigs()
    }
}


fun BaseAppModuleExtension.applyCommonConfigs() {

    defaultConfig {
        minSdkVersion(23)
        println("configurando o projeto")
        println(project.projectDir.name)
    }
}
