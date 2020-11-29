/**
 * Configure the sub-projects with common configuration
 */
subprojects {
    apply(plugin = "java")
    repositories {
        mavenLocal()
        jcenter()
    }
}
