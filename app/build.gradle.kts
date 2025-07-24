plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.hilt)
	alias(libs.plugins.ksp)
	id("de.mannodermaus.android-junit5") version libs.versions.junit5Android
}

android {
	namespace = "com.movie.tmdb"
	compileSdk = 36

	defaultConfig {
		applicationId = "com.movie.tmdb"
		minSdk = 24
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

		vectorDrawables {
			useSupportLibrary = true
		}

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
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}

	kotlin {
		compilerOptions {
			jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
		}
	}
	buildFeatures {
		compose = true
	}



	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {
	// Core bundles
	implementation(libs.bundles.core)
	implementation(libs.bundles.compose)
	implementation(libs.bundles.navigation)
	implementation(libs.bundles.hilt)
	implementation(libs.bundles.room)
	implementation(libs.bundles.networking)
	implementation(libs.bundles.coroutines)
	implementation(libs.bundles.image.loading)

	// Compose BOM
	implementation(platform(libs.androidx.compose.bom))

	// KSP processors
	ksp(libs.hilt.compiler)
	ksp(libs.room.compiler)
	ksp(libs.moshi.codegen)

	// Unit Testing
	androidTestImplementation(platform(libs.androidx.compose.bom))
	kspAndroidTest(libs.hilt.compiler)

	// Debug
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}
