plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.application) apply(false)
    alias(libs.plugins.android.library) apply(false)
    alias(libs.plugins.kotlin.android) apply(false)
    alias(libs.plugins.kotlin.multiplatform) apply(false)
}