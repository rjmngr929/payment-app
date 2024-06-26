# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    public <init>(...);
}

-keep class com.example.paymentapplication.services.BaseApplication { *; }

# Keep Dagger Hilt annotations
-keep class dagger.hilt.android.** { *; }
-keep class javax.inject.** { *; }

# Keep Dagger Hilt generated code
-keepclasseswithmembers class * {
    @dagger.hilt.* <methods>;
}

-keep class * implements dagger.hilt.android.internal.GeneratedComponent { *; }



-keep class shishirtstudio.com.proguardtest.data.network.apiResponse.** { *; }
