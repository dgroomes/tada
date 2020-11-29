plugins {
    `java-library`
}

val grpcVersion = "1.33.1"

dependencies {
    /*
     * These are the Protobuf and gRPC dependencies needed for Java. The three dependencies map to the high-level
     * components described in the 'grpc-java' project README: https://github.com/grpc/grpc-java#high-level-components
     *   1) Stub ('grpc-stub')
     *   2) Channel ('grpc-protobuf')
     *   3) Transport ('grpc-netty-shaded')
     */
    api("io.grpc:grpc-stub:$grpcVersion")
    api("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-netty-shaded:$grpcVersion")
    compileOnly("org.apache.tomcat:annotations-api:6.0.53") // why?
}
