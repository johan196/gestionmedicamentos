plugins {
    alias(libs.plugins.android.application)

}

android {
    namespace = "com.example.gestionmedicamentos"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.gestionmedicamentos"
        minSdk = 22
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.lottie)
    implementation(libs.constraintlayout)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}