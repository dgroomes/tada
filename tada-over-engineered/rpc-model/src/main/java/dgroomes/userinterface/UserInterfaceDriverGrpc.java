package dgroomes.userinterface;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 **
 * A service that drives a UI. (Remember, this whole project is abstract nonsense).
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: user-interface.proto")
public final class UserInterfaceDriverGrpc {

  private UserInterfaceDriverGrpc() {}

  public static final String SERVICE_NAME = "UserInterfaceDriver";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dgroomes.userinterface.UserInterfaceProtos.ClientRequest,
      dgroomes.userinterface.UserInterfaceProtos.Instruction> getNextInstructionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NextInstruction",
      requestType = dgroomes.userinterface.UserInterfaceProtos.ClientRequest.class,
      responseType = dgroomes.userinterface.UserInterfaceProtos.Instruction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dgroomes.userinterface.UserInterfaceProtos.ClientRequest,
      dgroomes.userinterface.UserInterfaceProtos.Instruction> getNextInstructionMethod() {
    io.grpc.MethodDescriptor<dgroomes.userinterface.UserInterfaceProtos.ClientRequest, dgroomes.userinterface.UserInterfaceProtos.Instruction> getNextInstructionMethod;
    if ((getNextInstructionMethod = UserInterfaceDriverGrpc.getNextInstructionMethod) == null) {
      synchronized (UserInterfaceDriverGrpc.class) {
        if ((getNextInstructionMethod = UserInterfaceDriverGrpc.getNextInstructionMethod) == null) {
          UserInterfaceDriverGrpc.getNextInstructionMethod = getNextInstructionMethod =
              io.grpc.MethodDescriptor.<dgroomes.userinterface.UserInterfaceProtos.ClientRequest, dgroomes.userinterface.UserInterfaceProtos.Instruction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NextInstruction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dgroomes.userinterface.UserInterfaceProtos.ClientRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dgroomes.userinterface.UserInterfaceProtos.Instruction.getDefaultInstance()))
              .setSchemaDescriptor(new UserInterfaceDriverMethodDescriptorSupplier("NextInstruction"))
              .build();
        }
      }
    }
    return getNextInstructionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserInterfaceDriverStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserInterfaceDriverStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserInterfaceDriverStub>() {
        @java.lang.Override
        public UserInterfaceDriverStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserInterfaceDriverStub(channel, callOptions);
        }
      };
    return UserInterfaceDriverStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserInterfaceDriverBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserInterfaceDriverBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserInterfaceDriverBlockingStub>() {
        @java.lang.Override
        public UserInterfaceDriverBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserInterfaceDriverBlockingStub(channel, callOptions);
        }
      };
    return UserInterfaceDriverBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserInterfaceDriverFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserInterfaceDriverFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserInterfaceDriverFutureStub>() {
        @java.lang.Override
        public UserInterfaceDriverFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserInterfaceDriverFutureStub(channel, callOptions);
        }
      };
    return UserInterfaceDriverFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * A service that drives a UI. (Remember, this whole project is abstract nonsense).
   * </pre>
   */
  public static abstract class UserInterfaceDriverImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     **
     * Calling this method returns the "next UI instruction"
     * </pre>
     */
    public void nextInstruction(dgroomes.userinterface.UserInterfaceProtos.ClientRequest request,
        io.grpc.stub.StreamObserver<dgroomes.userinterface.UserInterfaceProtos.Instruction> responseObserver) {
      asyncUnimplementedUnaryCall(getNextInstructionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getNextInstructionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dgroomes.userinterface.UserInterfaceProtos.ClientRequest,
                dgroomes.userinterface.UserInterfaceProtos.Instruction>(
                  this, METHODID_NEXT_INSTRUCTION)))
          .build();
    }
  }

  /**
   * <pre>
   **
   * A service that drives a UI. (Remember, this whole project is abstract nonsense).
   * </pre>
   */
  public static final class UserInterfaceDriverStub extends io.grpc.stub.AbstractAsyncStub<UserInterfaceDriverStub> {
    private UserInterfaceDriverStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserInterfaceDriverStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserInterfaceDriverStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Calling this method returns the "next UI instruction"
     * </pre>
     */
    public void nextInstruction(dgroomes.userinterface.UserInterfaceProtos.ClientRequest request,
        io.grpc.stub.StreamObserver<dgroomes.userinterface.UserInterfaceProtos.Instruction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNextInstructionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   **
   * A service that drives a UI. (Remember, this whole project is abstract nonsense).
   * </pre>
   */
  public static final class UserInterfaceDriverBlockingStub extends io.grpc.stub.AbstractBlockingStub<UserInterfaceDriverBlockingStub> {
    private UserInterfaceDriverBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserInterfaceDriverBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserInterfaceDriverBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Calling this method returns the "next UI instruction"
     * </pre>
     */
    public dgroomes.userinterface.UserInterfaceProtos.Instruction nextInstruction(dgroomes.userinterface.UserInterfaceProtos.ClientRequest request) {
      return blockingUnaryCall(
          getChannel(), getNextInstructionMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   **
   * A service that drives a UI. (Remember, this whole project is abstract nonsense).
   * </pre>
   */
  public static final class UserInterfaceDriverFutureStub extends io.grpc.stub.AbstractFutureStub<UserInterfaceDriverFutureStub> {
    private UserInterfaceDriverFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserInterfaceDriverFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserInterfaceDriverFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Calling this method returns the "next UI instruction"
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<dgroomes.userinterface.UserInterfaceProtos.Instruction> nextInstruction(
        dgroomes.userinterface.UserInterfaceProtos.ClientRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNextInstructionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_NEXT_INSTRUCTION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserInterfaceDriverImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserInterfaceDriverImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NEXT_INSTRUCTION:
          serviceImpl.nextInstruction((dgroomes.userinterface.UserInterfaceProtos.ClientRequest) request,
              (io.grpc.stub.StreamObserver<dgroomes.userinterface.UserInterfaceProtos.Instruction>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UserInterfaceDriverBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserInterfaceDriverBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dgroomes.userinterface.UserInterfaceProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserInterfaceDriver");
    }
  }

  private static final class UserInterfaceDriverFileDescriptorSupplier
      extends UserInterfaceDriverBaseDescriptorSupplier {
    UserInterfaceDriverFileDescriptorSupplier() {}
  }

  private static final class UserInterfaceDriverMethodDescriptorSupplier
      extends UserInterfaceDriverBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserInterfaceDriverMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserInterfaceDriverGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserInterfaceDriverFileDescriptorSupplier())
              .addMethod(getNextInstructionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
