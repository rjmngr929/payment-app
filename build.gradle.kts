// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    id ("com.android.library") version "7.3.0" apply false

    id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
    id("com.github.dcendents.android-maven") version "2.1" apply false

    id("com.google.dagger.hilt.android") version "2.51.1" apply false

}