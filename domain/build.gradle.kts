plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.devtools.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "br.com.cpcjrdev.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.test)
    // Dependency Injection (Hilt)
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    // Unit Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
}
