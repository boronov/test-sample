plugins {
    id(Plugins.AGP.application)
    kotlin(Plugins.Kotlin.android)
    kotlin(Plugins.Kotlin.kapt)

    // Navigation Safe Args
    id(Plugins.Navigation.safeArgs)

    // Hilt
    id(Plugins.Hilt.plugin)
}

android {
    compileSdk = AndroidConfig.compileSdk
    namespace = "ru.appsmile.test.hotel"

    defaultConfig {
        applicationId = "ru.appsmile.test.hotel"
        resourceConfigurations += listOf("en", "ru", "tg", "uz")

        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

        versionCode = AndroidConfig.App.majorVersion * 10000 + AndroidConfig.App.minorVersion * 100 + AndroidConfig.App.patchVersion
        versionName = "${AndroidConfig.App.majorVersion}.${AndroidConfig.App.minorVersion}.${AndroidConfig.App.patchVersion}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName(AndroidConfig.release) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        getByName(AndroidConfig.debug) {
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = Options.compileOptions
        targetCompatibility = Options.compileOptions
    }

    kotlinOptions {
        jvmTarget = Options.kotlinOptions
    }

    // View Binding
    viewBinding.isEnabled = true
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    // Core
    implementation(Libraries.Core.core)
    implementation(Libraries.Core.appcompat)
    implementation(Libraries.Core.jodaTime)
    implementation(Libraries.Core.keyboardvisibilityevent)

    // UI
    implementation(Libraries.UIComponents.material)
    implementation(Libraries.UIComponents.constraintLayout)
    implementation(Libraries.UIComponents.swiperefreshlayout)
    implementation(Libraries.UIComponents.shimmer)
    implementation(Libraries.UIComponents.glide)

    implementation(Libraries.Activity.activity)
    implementation(Libraries.Fragment.fragment)

    implementation(Libraries.Coroutines.android)

    // Lifecycle
    implementation(Libraries.Lifecycle.viewModel)
    implementation(Libraries.Lifecycle.runtime)

    // Navigation
    implementation(Libraries.Navigation.fragment)
    implementation(Libraries.Navigation.ui)

    // Hilt
    implementation(Libraries.Hilt.android)
    kapt(Libraries.Hilt.compiler)

    // OkHttp
    implementation(Libraries.OkHttp.bom)
    implementation(Libraries.OkHttp.okHttp)
    implementation(Libraries.OkHttp.loggingInterceptor)
}