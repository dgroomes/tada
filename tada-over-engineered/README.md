# tada-over-engineered

NOT YET FULLY IMPLEMENTED

This is an experimental attempt to make an [over-engineered](https://en.wikipedia.org/wiki/Overengineering) version of
the `tada` project. Why? I'd like to use the project mainly as a vehicle to learn and integrate various technologies
(gRPC, Android, Electron, and more) for my own learning and self-reference.

## System Architecture

The system is made of these components:

1. `rpc-server` is a Java-based gRPC server that will send UI instructions to clients. In theory, it should contain
   enough complex logic to make the client/server architecture worth the expense and overhead.
1. `test-client` is an Electron app backed by a gRPC client that will connect to `rpc-server` to receive UI instructions.
   The client will execute the instructions in the Chromium window. This client is used just for development purposes to
   exercise and test `rpc-server`. The `android` sub-project is the real client but it's slow to iterate with Android
   development so I will instead iterate using `test-client`. Also, having a full-blown Electron client just for testing
   purposes is an instance of over-engineering. Perfect!
1. `rpc-model` contains the Protocol Buffer's `.proto` definition files and the generated JavaScript- and Java-based
   Protobuf and gRPC code.      
1. NOT STARTED `android` is an Android app that re-implements the `tada` app but in an over-engineered way. `android` will connect
   to `rpc-server` over gRPC to receive its UI instructions and then will execute them. 


## Instructions

1. Use Java 15.
1. Install `protoc`. This is the Protocol Buffers compiler.
    * Download it via the Protocol Buffers [GitHub Releases page](https://github.com/protocolbuffers/protobuf/releases).
      For example, [protoc-3.14.0-osx-x86_64.zip](https://github.com/protocolbuffers/protobuf/releases/download/v3.14.0/protoc-3.14.0-osx-x86_64.zip)
    * Add it to your `PATH`
1. Install the Java gRPC extension for `protoc`
    * Follow the note in [`grpc-java`'s README for the compiler](https://github.com/grpc/grpc-java/tree/master/compiler):
      > Normally you don't need to compile the codegen by yourself, since pre-compiled binaries for common platforms are available on Maven Central:
      >  
      > 1. Navigate to https://mvnrepository.com/artifact/io.grpc/protoc-gen-grpc-java
      > 1. Click into a version
      > 1. Click "Files"
    * For example, [protoc-gen-grpc-java-1.33.1-osx-x86_64.exe](https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/1.33.1/protoc-gen-grpc-java-1.33.1-osx-x86_64.exe)
    * Make it executable, move it somewhere on your PATH, and rename it to *exactly* `protoc-gen-java-grpc`. For example
      I executed these commands on my computer:
      ```
      mv ~/Downloads/protoc-gen-grpc-java-1.33.1-osx-x86_64.exe /usr/local/bin/protoc-gen-java-grpc
      chmod +x /usr/local/bin/protoc-gen-java-grpc
      ```
1. Generate the Java source code from the Protobuf files
    * Move into the `rpc-model/` sub-project: `cd rpc-model`
    * Generate the Java source with: `./generate-protobuf-code.sh`
    * This will have created the files `src/main/java/dgroomes/EchoProtos.java` and `src/main/java/dgroomes/EchoGrpc.java`
    * I am choosing to check these files into version control. Alternatively, in your own project, you could gitignore
      the generated code and just build them on-demand as part of your development workflow. That would be the more
      "engineered" approach.
    * Return to the root of the sub-project: `cd ..` 
1. Build the project with `./gradlew installDist` 
1. Run the echo_server with `rpc-server/build/install/rpc-server/bin/rpc-server`. You should see output like this:
   ```
   Nov 23, 2020 10:05:32 PM dgroomes.ServerMain main
   INFO: Listening for requests...
   ```
1. Start the `test-client`
    * `cd test-client`
    * `npm install`
    * `npm start`