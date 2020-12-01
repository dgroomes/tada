package dgroomes;

import dgroomes.userinterface.UserInterfaceDriverGrpc.UserInterfaceDriverImplBase;
import dgroomes.userinterface.UserInterfaceProtos.ClientRequest;
import dgroomes.userinterface.UserInterfaceProtos.Instruction;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static java.time.temporal.ChronoField.*;

/**
 * A simple Java program that runs a gRPC server that sends User Interface (UI) instructions.
 * <p>
 * NOT YET FULLY IMPLEMENTED
 */
public class ServerMain {

    private static final Logger log = Logger.getLogger(ServerMain.class.getName());
    public static final int NUMBER_OF_INSTRUCTIONS = 100;
    public static final int INSTRUCTION_DELAY = 1000;

    /**
     * 1) Start a gRPC server
     * 2) Register a shutdown hook for a clean shutdown
     * 3) Wait indefinitely
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        var server = ServerBuilder.forPort(9090)
                .addService(new DriverImpl())
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

    static class DriverImpl extends UserInterfaceDriverImplBase {

        int idx = 1;

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .toFormatter();

        @Override
        public void nextInstructions(ClientRequest request, StreamObserver<Instruction> responseObserver) {
            int idx = 1;

            while (idx <= NUMBER_OF_INSTRUCTIONS) {
                try {
                    Thread.sleep(INSTRUCTION_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                var now = formatter.format(LocalDateTime.now());

                var instruction = Instruction.newBuilder()
                        .setTextContent("[%s] hello %d".formatted(now, idx++))
                        .build();
                responseObserver.onNext(instruction);

            }
            responseObserver.onCompleted();
        }
    }
}
