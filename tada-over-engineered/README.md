# tada-over-engineered

NOT YET IMPLEMENTED

This is an experimental attempt to make an [over-engineered](https://en.wikipedia.org/wiki/Overengineering) version of
the `tada` project. Why? I'd like to use the project mainly as a vehicle to learn and integrate various technologies
(gRPC, Android, Electron, and more) for my own learning and self-reference.

## System Architecture

NOT YET IMPLEMENTED

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
1. `android` is an Android app that re-implements the `tada` app but in an over-engineered way. `android` will connect
   to `rpc-server` over gRPC to receive its UI instructions and then will execute them. 
