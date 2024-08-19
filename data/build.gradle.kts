plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id(libs.plugins.sqldelight.get().pluginId)
}

android {
    namespace = "com.test.chatapp.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        android.buildFeatures.buildConfig = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }


}

dependencies {
    implementation(project(":data_api"))
    implementation(project(":domain_models"))

    implementation(libs.javax.inject)

    implementation(libs.core.ktx)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.json)
    implementation(libs.okhttp3.interceptor)

    implementation(libs.sqldelight.androidDriver)
    implementation (libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore)


    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
}

sqldelight {
    databases {
        create("DatabaseChat") {
            packageName.set("com.data.base")
            srcDirs.setFrom("src/main/sqldelight")
        }
    }
}