plugins {
    application
}

application {
    // Define the main class for the application.
    mainClass.set("dgroomes.ServerMain")
}

dependencies {
    implementation(project(":rpc-model"))
}
