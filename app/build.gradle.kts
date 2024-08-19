import java.util.Properties

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("kotlin-parcelize")
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
        android.buildFeatures.buildConfig = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures.buildConfig = true

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
        kotlinCompilerExtensionVersion = libs.versions.composecompiler.get()
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
    api(project(":core:ui"))
    implementation(project(":features:authorization"))
    implementation(project(":features:authorization_api"))
    implementation(project(":features:chat"))
    implementation(project(":features:chat_api"))
    implementation(project(":features:chat_list"))
    implementation(project(":features:chat_list_api"))
    implementation(project(":features:user_details"))
    implementation(project(":features:user_details_api"))



    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.core.ktx)

    implementation(libs.lifecycle.runtime.ktx)

    // ui compose
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.navigation)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.coil.compose)


    implementation (libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)


    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.json)
    implementation(libs.okhttp3.interceptor)
    implementation (libs.retrofit2.kotlin.coroutines.adapter)

    // tests
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
}



