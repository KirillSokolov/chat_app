import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("kotlin-kapt")

}

val properties = Properties().apply {
    rootProject.file("local.properties").reader().use(::load)
}

val baseApiUrl: String = properties.getProperty("BASE_API_URL") as String
val apiVersion: String = properties.getProperty("API_VERSION") as String

android {
    namespace = "com.test.chatapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.test.chatapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {

            isMinifyEnabled = false
            buildConfigField("String", "BASE_API_URL", "\"${baseApiUrl}\"")
            buildConfigField("String", "API_VERSION", "\"${apiVersion}\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":data_api"))
    implementation(project(":core:navigation"))

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.core.ktx)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.json)
    implementation(libs.okhttp3.interceptor)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    // tests
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
}