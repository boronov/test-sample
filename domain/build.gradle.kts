plugins {
    kotlin(Plugins.Kotlin.jvm)
}

java {
    sourceCompatibility = Options.compileOptions
    targetCompatibility = Options.compileOptions
}

dependencies {
    implementation(Libraries.Coroutines.core)
    implementation(Libraries.Javax.inject)
}