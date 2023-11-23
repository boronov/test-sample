object Versions {
    const val AGP = "8.1.2"
    const val kotlin = "1.8.0"
    const val coroutines = "1.7.3"
    const val KSP = "1.7.21-1.0.8"
    const val material = "1.7.0"
    const val constraintLayout = "2.1.4"
    const val core = "1.9.0"
    const val activity = "1.6.1"
    const val fragment = "1.5.5"
    const val lifecycle = "2.5.1"
    const val navigation = "2.5.3"
    const val retrofit = "2.9.0"
    const val okHttp = "5.0.0-alpha.10"
    const val glide = "4.15.1"
    const val swiperefreshlayout = "1.1.0"
    const val appcompat = "1.7.0-alpha01"
    const val keyboardvisibilityevent = "3.0.0-RC3"
    const val shimmer = "0.5.0"
    const val jodaTime = "2.11.2"
    const val dagger = "2.47"

}

object Libraries {

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object UIComponents {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
        const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    }

    object Core {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val keyboardvisibilityevent = "net.yslibrary.keyboardvisibilityevent:keyboardvisibilityevent:${Versions.keyboardvisibilityevent}"
        const val jodaTime = "joda-time:joda-time:${Versions.jodaTime}"
    }

    object Activity {
        const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
    }

    object Fragment {
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    }

    object Lifecycle {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object OkHttp {
        const val bom = "com.squareup.okhttp3:okhttp-bom:${Versions.okHttp}"
        const val okHttp = "com.squareup.okhttp3:okhttp"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor"
    }

    object Javax {
        const val inject = "javax.inject:javax.inject:1"
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.dagger}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"
    }
}

object Plugins {

    object AGP {
        const val application = "com.android.application"
        const val library = "com.android.library"
    }

    object Kotlin {
        const val android = "android"
        const val jvm = "jvm"
        const val kapt = "kapt"
    }

    object KSP {
        const val ksp = "com.google.devtools.ksp"
    }

    object Navigation {
        const val safeArgs = "androidx.navigation.safeargs.kotlin"
    }

    object Hilt {
        const val plugin = "com.google.dagger.hilt.android"
    }
}