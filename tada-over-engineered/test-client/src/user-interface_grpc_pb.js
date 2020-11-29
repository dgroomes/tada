// GENERATED CODE -- DO NOT EDIT!

'use strict';
var grpc = require('@grpc/grpc-js');
var user$interface_pb = require('./user-interface_pb.js');

function serialize_ClientRequest(arg) {
  if (!(arg instanceof user$interface_pb.ClientRequest)) {
    throw new Error('Expected argument of type ClientRequest');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_ClientRequest(buffer_arg) {
  return user$interface_pb.ClientRequest.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_Instruction(arg) {
  if (!(arg instanceof user$interface_pb.Instruction)) {
    throw new Error('Expected argument of type Instruction');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_Instruction(buffer_arg) {
  return user$interface_pb.Instruction.deserializeBinary(new Uint8Array(buffer_arg));
}


// *
// A service that drives a UI. (Remember, this whole project is abstract nonsense).
var UserInterfaceDriverService = exports.UserInterfaceDriverService = {
  // *
// Calling this method returns the "next UI instruction"
nextInstruction: {
    path: '/UserInterfaceDriver/NextInstruction',
    requestStream: false,
    responseStream: false,
    requestType: user$interface_pb.ClientRequest,
    responseType: user$interface_pb.Instruction,
    requestSerialize: serialize_ClientRequest,
    requestDeserialize: deserialize_ClientRequest,
    responseSerialize: serialize_Instruction,
    responseDeserialize: deserialize_Instruction,
  },
};

exports.UserInterfaceDriverClient = grpc.makeGenericClientConstructor(UserInterfaceDriverService);
