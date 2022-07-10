object Versions {
    const val kotlin = "1.6.20"
    const val material = "1.4.0"
    const val appCompat = "1.3.1"
    const val constraint = "2.1.0"
    const val navFragment = "2.3.5"
    const val navUi = "2.3.5"
    const val livedata = "2.3.1"
    const val multiplatformSettings = "0.8.1"
    const val compose = "1.2.0-alpha08"
    const val activityCompose = "1.4.0"
    const val composeDestinations = "1.5.1-beta"
    const val coilAccompanist = "0.15.0"
    const val pagerAccompanist = "0.15.0"
    const val kermit = "0.3.0-m1"
    const val koin = "3.1.4"
    const val coroutine = "1.6.0-native-mt"
    const val serialization = "1.3.0"
    const val dateTime = "0.3.1"
    const val ktor = "2.0.0"
    const val realm = "0.11.1"
    const val lottie = "4.0.0"
    const val shimmer = "1.0.2"
}

object Dependencies {
    const val ktxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
    const val ktxCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val ktxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.dateTime}"

    const val androidMaterial = "com.google.android.material:material:${Versions.material}"
    const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val fragmentNavigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navFragment}"
    const val androidNavigation = "androidx.navigation:navigation-ui-ktx:${Versions.navUi}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata}"

    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val composeUtil = "androidx.compose.ui:ui-util:${Versions.compose}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val composeFoundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val composeMaterialIcon = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"

    const val composeActivity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeLottie = "com.airbnb.android:lottie-compose:${Versions.lottie}"
    const val composeShimmer = "com.valentinilk.shimmer:compose-shimmer:${Versions.shimmer}"

    const val accompanistCoil = "com.google.accompanist:accompanist-coil:${Versions.coilAccompanist}"
    const val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.pagerAccompanist}"

    const val composeDestinations = "io.github.raamcosta.compose-destinations:core:${Versions.composeDestinations}"
    const val composeDestinationsAnimation = "io.github.raamcosta.compose-destinations:animations-core:${Versions.composeDestinations}"
    const val composeDestinationsKsp = "io.github.raamcosta.compose-destinations:ksp:${Versions.composeDestinations}"

    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"

    const val ktorCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val ktorJsonSerialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    const val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val ktorOkhttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
    const val ktorDarwin = "io.ktor:ktor-client-darwin:${Versions.ktor}"
    const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ktorIos = "io.ktor:ktor-client-ios:${Versions.ktor}"

    const val realmKotlin = "io.realm.kotlin:library-base:${Versions.realm}"

    const val kermitLogger = "co.touchlab:kermit:${Versions.kermit}"
    const val multiplatformSettings = "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
}








