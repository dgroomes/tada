buildscript {
    repositories {
        mavenLocal()
        jcenter()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1") // releases: https://developer.android.com/studio/releases/gradle-plugin
        classpath(kotlin("gradle-plugin", version = "1.4.20"))
    }
}

// Configure all sub-projects
allprojects {
    repositories {
        mavenLocal()
        jcenter()
        google()
    }
}
