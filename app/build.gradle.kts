import java.util.Properties
import java.text.SimpleDateFormat
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.util.Date


@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.gradle)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.osedhelu.bnc"
    compileSdk = 33
    val localProperties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localProperties.load(localPropertiesFile.inputStream())
    }
    // Obtener las propiedades necesarias de local.properties
    val getPropertyValue: (String) -> String = { key ->
        localProperties.getProperty(key)
            ?: throw IllegalArgumentException("Property '$key' not found in local.properties")
    }

    defaultConfig {
        applicationId = "com.osedhelu.bnc"
        minSdk = 21
        targetSdk = 33
        versionName = "1.0"
        versionCode = autoIncrementVersionCode()
        
        ///buildConfigField("String", "SERVER_URL", getPropertyValue("SERVER_URL"))
        

        testInstrumentationRunner = "com.osedhelu.bnc.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Enable room auto-migrations
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
    applicationVariants.all {
        val variant = this
        val appName = "nexgobnc"
        val date = SimpleDateFormat("yyyyMMdd").format(Date())
        val versionCode = variant.versionCode
        val variantName = variant.name
        variant.outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName = "${appName}${date}${versionCode}_${variantName}.apk"

                println("OutputFileName: $outputFileName")
                output.outputFileName = outputFileName
            }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        aidl = false
        buildConfig = true 
        renderScript = false
        shaders = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
    implementation(files("libs/nexgo-smartpos-sdk-v2.2.1_20190527.aar"))
    implementation(libs.socketio)
    implementation(libs.runtime)
    implementation(libs.runtime.rxjava2)
    implementation(libs.runtime.livedata)
    implementation(libs.lottie)
    implementation(libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.jetbrains.kotlin)



    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // Hilt and instrumented tests.
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
    // Hilt and Robolectric tests.
    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.hilt.android.compiler)

    // Arch Components
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    // Tooling
    debugImplementation(libs.androidx.compose.ui.tooling)
    // Instrumented tests
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Local tests: jUnit, coroutines, Android runner
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    // Instrumented tests: jUnit rules and runners

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
}

fun autoIncrementVersionCode(): Int? {
    var code = 1
    val versionCodeFile = File("version_code.txt")

    if (versionCodeFile.exists()) {
        code = versionCodeFile.readText().toInt()
        code++
    }
    versionCodeFile.writeText(code.toString())
    return code
}