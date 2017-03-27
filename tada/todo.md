### TODOs
- make a main screen, where options can exist, and a "go" button to get to the Art screen
- cleanup code and simplify; maybe get rid of Osciallator and use some standard library facility as substitute
- upgrade versions
- cleanup build.gradle; make it idiommatic; i'm not sure what half the stuff is in there
- why is it necessary to have both a `compile` and `testCompile` dependency on `testCompile 'com.android.support:appcompat-v7:25.2.0'`

#### Backburner TODOs
- I would really like to use AutoFactory (https://github.com/google/auto/tree/master/factory) but code generation does
not work well with non-Java, rather Kotlin. There is a mess of build-tool configuration required for figuring this out,
and probably a major pull request for the `kapt` tool at https://github.com/JetBrains/kotlin
