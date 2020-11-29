package dgroomes;

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
 * This is a simple RPC service. It's like an interface declaration in the Protobuf language. In my opinion, it's more
 * useful to think of this as an interface and not a service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: echo.proto")
public final class EchoGrpc {

  private EchoGrpc() {}

  public static final String SERVICE_NAME = "Echo";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dgroomes.EchoProtos.Message,
      dgroomes.EchoProtos.Message> getEchoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Echo",
      requestType = dgroomes.EchoProtos.Message.class,
      responseType = dgroomes.EchoProtos.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dgroomes.EchoProtos.Message,
      dgroomes.EchoProtos.Message> getEchoMethod() {
    io.grpc.MethodDescriptor<dgroomes.EchoProtos.Message, dgroomes.EchoProtos.Message> getEchoMethod;
    if ((getEchoMethod = EchoGrpc.getEchoMethod) == null) {
      synchronized (EchoGrpc.class) {
        if ((getEchoMethod = EchoGrpc.getEchoMethod) == null) {
          EchoGrpc.getEchoMethod = getEchoMethod =
              io.grpc.MethodDescriptor.<dgroomes.EchoProtos.Message, dgroomes.EchoProtos.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Echo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dgroomes.EchoProtos.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dgroomes.EchoProtos.Message.getDefaultInstance()))
              .setSchemaDescriptor(new EchoMethodDescriptorSupplier("Echo"))
              .build();
        }
      }
    }
    return getEchoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EchoStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EchoStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EchoStub>() {
        @java.lang.Override
        public EchoStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EchoStub(channel, callOptions);
        }
      };
    return EchoStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EchoBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EchoBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EchoBlockingStub>() {
        @java.lang.Override
        public EchoBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EchoBlockingStub(channel, callOptions);
        }
      };
    return EchoBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EchoFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EchoFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EchoFutureStub>() {
        @java.lang.Override
        public EchoFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EchoFutureStub(channel, callOptions);
        }
      };
    return EchoFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * This is a simple RPC service. It's like an interface declaration in the Protobuf language. In my opinion, it's more
   * useful to think of this as an interface and not a service.
   * </pre>
   */
  public static abstract class EchoImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * This is a simple RPC method that echos back some message. Well, it's not really a method but rather the interface
     * of a method. It's up to the implementing server-side code to actually implement the echo functionality. There's
     * nothing stopping the server-side implementation from just returning a random string or an empty string instead of
     * actually echoing back the same string.
     * </pre>
     */
    public void echo(dgroomes.EchoProtos.Message request,
        io.grpc.stub.StreamObserver<dgroomes.EchoProtos.Message> responseObserver) {
      asyncUnimplementedUnaryCall(getEchoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getEchoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dgroomes.EchoProtos.Message,
                dgroomes.EchoProtos.Message>(
                  this, METHODID_ECHO)))
          .build();
    }
  }

  /**
   * <pre>
   * This is a simple RPC service. It's like an interface declaration in the Protobuf language. In my opinion, it's more
   * useful to think of this as an interface and not a service.
   * </pre>
   */
  public static final class EchoStub extends io.grpc.stub.AbstractAsyncStub<EchoStub> {
    private EchoStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EchoStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EchoStub(channel, callOptions);
    }

    /**
     * <pre>
     * This is a simple RPC method that echos back some message. Well, it's not really a method but rather the interface
     * of a method. It's up to the implementing server-side code to actually implement the echo functionality. There's
     * nothing stopping the server-side implementation from just returning a random string or an empty string instead of
     * actually echoing back the same string.
     * </pre>
     */
    public void echo(dgroomes.EchoProtos.Message request,
        io.grpc.stub.StreamObserver<dgroomes.EchoProtos.Message> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEchoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * This is a simple RPC service. It's like an interface declaration in the Protobuf language. In my opinion, it's more
   * useful to think of this as an interface and not a service.
   * </pre>
   */
  public static final class EchoBlockingStub extends io.grpc.stub.AbstractBlockingStub<EchoBlockingStub> {
    private EchoBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EchoBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EchoBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * This is a simple RPC method that echos back some message. Well, it's not really a method but rather the interface
     * of a method. It's up to the implementing server-side code to actually implement the echo functionality. There's
     * nothing stopping the server-side implementation from just returning a random string or an empty string instead of
     * actually echoing back the same string.
     * </pre>
     */
    public dgroomes.EchoProtos.Message echo(dgroomes.EchoProtos.Message request) {
      return blockingUnaryCall(
          getChannel(), getEchoMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * This is a simple RPC service. It's like an interface declaration in the Protobuf language. In my opinion, it's more
   * useful to think of this as an interface and not a service.
   * </pre>
   */
  public static final class EchoFutureStub extends io.grpc.stub.AbstractFutureStub<EchoFutureStub> {
    private EchoFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EchoFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EchoFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * This is a simple RPC method that echos back some message. Well, it's not really a method but rather the interface
     * of a method. It's up to the implementing server-side code to actually implement the echo functionality. There's
     * nothing stopping the server-side implementation from just returning a random string or an empty string instead of
     * actually echoing back the same string.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<dgroomes.EchoProtos.Message> echo(
        dgroomes.EchoProtos.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getEchoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ECHO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EchoImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EchoImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ECHO:
          serviceImpl.echo((dgroomes.EchoProtos.Message) request,
              (io.grpc.stub.StreamObserver<dgroomes.EchoProtos.Message>) responseObserver);
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

  private static abstract class EchoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EchoBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dgroomes.EchoProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Echo");
    }
  }

  private static final class EchoFileDescriptorSupplier
      extends EchoBaseDescriptorSupplier {
    EchoFileDescriptorSupplier() {}
  }

  private static final class EchoMethodDescriptorSupplier
      extends EchoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EchoMethodDescriptorSupplier(String methodName) {
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
      synchronized (EchoGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EchoFileDescriptorSupplier())
              .addMethod(getEchoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
