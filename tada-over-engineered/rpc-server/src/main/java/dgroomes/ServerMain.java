package dgroomes;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * A simple Java program that runs a gRPC server.
 */
public class ServerMain {

    private static final Logger log = Logger.getLogger(ServerMain.class.getName());

    /**
     * 1) Start a gRPC server
     * 2) Register a shutdown hook for a clean shutdown
     * 3) Wait indefinitely
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        var server = ServerBuilder.forPort(9090)
                .addService(new EchoImpl())
                .build()
                .start();
        log.info("Listening for requests...");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Print to standard error because it's too late in the JVM lifecycle for java.util.logging to actually
                // keep working (I wish I knew how this worked).
                System.err.println("Shutdown hook triggered. Shutting down the gRPC server.");
                try {
                    server.shutdown().awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        });
        server.awaitTermination();
    }

    static class EchoImpl extends EchoGrpc.EchoImplBase {

        @Override
        public void echo(EchoProtos.Message req, StreamObserver<EchoProtos.Message> responseObserver) {
            var receivedMsg = req.getMessage();
            var responseMsg = EchoProtos.Message.newBuilder().setMessage("""
                    %s...
                    %s...
                    %s...
                    """.formatted(receivedMsg, receivedMsg, receivedMsg)).build();
            responseObserver.onNext(responseMsg);
            responseObserver.onCompleted();
        }
    }
}
