# tada

⏯ Demo Android application.

## Overview

Let's learn and explore Android with a toy/demo application.

I wrote this about 5 years ago and only now (2020) I'm adding a README and reviving this
project! I'd like to brush up on my Android knowledge and learn the new features and idioms. UPDATE:
got the project to build and run! Wow I'm amazed, impressed and thankful to the Android team for
letting this five year old project build in Android Studio without any source code changes at all!
In particular, I'm surprised that Android Studio allows a project using Gradle 3.3 and using the
Android Gradle plugin with version 2.3.1. I thought new releases of Android Studio required new
versions of the Android Gradle plugin? The backwards-compatibility story is much better than I
remembered.

## Notes

Use the command line to start the app in the attached device or emulator with:
```
adb shell am start -n "us.mn.dgtc.tada/us.mn.dgtc.tada.activity.HomeActivity"
```

## Wish List

General clean ups, TODOs and things I wish to implement for this project:

* Upgrade to latest version of Dagger
* Upgrade to latest version of the Android Gradle plugin
* Upgrade to latest version of Gradle
* Upgrade to latest version of Kotlin. (There's an overarching theme of "upgrade everything"! The
  Android development toolchain has tight coupling of the versions of these things. So, I have to
  find the right order to upgrade the versions of these dependencies. I would prefer to
  incrementally upgrade them and always stay within reach of a working codebase instead of trying
  the "roll the dice" approach of upgrading everything all at once and then scratching my head for
  an indeterminate amount of time as I get vague and complex build errrors (e.g 50+ line Gradle
  stacktraces, or internal errors in AAPT, or Dagger code generation problems especially with regard
  to Kotlin... I've been down this road before and I don't want to revisit it if I can help it!)
* Use Gradle's JVM toolchain support to use latest Java (15 right now) for the Gradle process but
  use Java 8 for the project itself (I doubt this would work)
