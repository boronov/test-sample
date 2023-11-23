plugins {
    id(Plugins.AGP.library)
    kotlin(Plugins.Kotlin.android)
}

android {
    namespace = "ru.appsmile.test.hotel.data"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", "\"https://run.mocky.io/v3/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Options.compileOptions
        targetCompatibility = Options.compileOptions
    }

    kotlinOptions {
        jvmTarget = Options.kotlinOptions
    }
}

dependencies {
    // Domain common
    implementation(project(":domain"))

    // Retrofit
    implementation(Libraries.Retrofit.retrofit)
    implementation(Libraries.Retrofit.converterMoshi)

    // OkHttp
    implementation(Libraries.OkHttp.bom)
    implementation(Libraries.OkHttp.okHttp)
    implementation(Libraries.OkHttp.loggingInterceptor)

    implementation(Libraries.Javax.inject)
    implementation(Libraries.Coroutines.android)
}