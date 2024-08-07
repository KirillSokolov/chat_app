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
    implementation(project(":core:ui"))
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

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.json)
    implementation(libs.okhttp3.interceptor)
    implementation (libs.retrofit2.kotlin.coroutines.adapter)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    implementation (libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore)
    // tests
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
}



