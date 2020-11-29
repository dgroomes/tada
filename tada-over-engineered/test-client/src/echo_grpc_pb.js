// GENERATED CODE -- DO NOT EDIT!

'use strict';
var grpc = require('@grpc/grpc-js');
var echo_pb = require('./echo_pb.js');

function serialize_Message(arg) {
  if (!(arg instanceof echo_pb.Message)) {
    throw new Error('Expected argument of type Message');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_Message(buffer_arg) {
  return echo_pb.Message.deserializeBinary(new Uint8Array(buffer_arg));
}


//
// This is a simple RPC service. It's like an interface declaration in the Protobuf language. In my opinion, it's more
// useful to think of this as an interface and not a service.
var EchoService = exports.EchoService = {
  //
// This is a simple RPC method that echos back some message. Well, it's not really a method but rather the interface
// of a method. It's up to the implementing server-side code to actually implement the echo functionality. There's
// nothing stopping the server-side implementation from just returning a random string or an empty string instead of
// actually echoing back the same string.
echo: {
    path: '/Echo/Echo',
    requestStream: false,
    responseStream: false,
    requestType: echo_pb.Message,
    responseType: echo_pb.Message,
    requestSerialize: serialize_Message,
    requestDeserialize: deserialize_Message,
    responseSerialize: serialize_Message,
    responseDeserialize: deserialize_Message,
  },
};

exports.EchoClient = grpc.makeGenericClientConstructor(EchoService);
