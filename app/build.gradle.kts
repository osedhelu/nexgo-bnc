import com.android.build.api.dsl.DefaultConfig
import com.android.build.gradle.api.ApplicationVariant
import java.util.Properties
import java.text.SimpleDateFormat
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.util.Date
import java.io.File

@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.application) version "8.1.0" // Actualizar a una versión que soporte compileSdk 34
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.gradle)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.disglobal.bnc"
    compileSdk = 34
    val localProperties = loadLocalProperties()
    val getPropertyValue: (String) -> String = { key ->
        localProperties.getProperty(key)
            ?: throw IllegalArgumentException("Property '$key' not found in local.properties")
    }

    defaultConfig {
        applicationId = "com.disglobal.bnc"
        minSdk = 21
        targetSdk = 33 // Cambiar de 33 a 34
        versionCode = autoIncrementVersionCode()
        versionName = autoIncrementVersionName()

        configureBuildConfigFields(getPropertyValue)

        testInstrumentationRunner = "com.disglobal.bnc.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    applicationVariants.all {
        configureVariantOutput(this)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
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
    implementation("androidx.appcompat:appcompat:1.7.0-alpha01") // Actualizar a una versión compatible
    implementation("androidx.core:core-ktx:1.13.0-alpha01") // Actualizar a una versión compatible
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
    implementation(files("libs/nexgo-smartpos-sdk-v3.08.002_20240410.aar"))
//    implementation(libs.socketio)
    implementation(libs.androidx.core.splashscreen)
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
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
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
    debugImplementation(libs.androidx.compose.ui.tooling)
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

fun loadLocalProperties(): Properties {
    val properties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        properties.load(localPropertiesFile.inputStream())
    }
    return properties
}

fun autoIncrementVersionCode(): Int {
    val versionCodeFile = File("version_code.txt")
    val code = if (versionCodeFile.exists()) {
        versionCodeFile.readText().toInt() + 1
    } else {
        1
    }
    versionCodeFile.writeText(code.toString())
    return code
}

fun DefaultConfig.configureBuildConfigFields(getPropertyValue: (String) -> String) {
    val envLocal: (name: String, type: String) -> Unit = { name, type ->
        val value = getPropertyValue(name)
        buildConfigField(type, name, "\"$value\"")
    }
    envLocal("baseUrl", "String")
    envLocal("appName", "String")
    envLocal("appVersion", "String")
    envLocal("ksn", "String")
    envLocal("affiliationId", "String")
    envLocal("userId", "String")
    envLocal("merchant", "String")
    envLocal("terminal", "String")
    envLocal("lotNumber", "String")
}

fun configureVariantOutput(variant: ApplicationVariant) {
    val appName = "nexgobnc"
    val date = SimpleDateFormat("yyyyMMdd").format(Date())
    val versionCode = variant.versionCode
    val variantName = variant.name
    variant.outputs.map { it as BaseVariantOutputImpl }.forEach { output ->
        val outputFileName = "${appName}${date}${versionCode}_${variantName}.apk"
        println("OutputFileName: $outputFileName")
        output.outputFileName = outputFileName
    }
}

fun autoIncrementVersionName(): String {
    val versionNameFile = File("version_name.txt")
    val versionName = if (versionNameFile.exists()) {
        val parts = versionNameFile.readText().split(".")
        val major = parts[0].toInt()
        val minor = parts[1].toInt()
        val patch = parts[2].toInt() + 1
        "$major.$minor.$patch"
    } else {
        "1.0.0"
    }
    versionNameFile.writeText(versionName)
    return versionName
}