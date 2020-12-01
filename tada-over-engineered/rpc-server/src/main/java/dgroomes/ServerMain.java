package dgroomes;

import dgroomes.userinterface.UserInterfaceDriverGrpc.UserInterfaceDriverImplBase;
import dgroomes.userinterface.UserInterfaceProtos.ClientRequest;
import dgroomes.userinterface.UserInterfaceProtos.Instruction;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * A simple Java program that runs a gRPC server that sends User Interface (UI) instructions.
 * <p>
 * NOT YET FULLY IMPLEMENTED
 */
public class ServerMain {

    private static final Logger log = Logger.getLogger(ServerMain.class.getName());
    public static final int NUMBER_OF_INSTRUCTIONS = 10;
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

        @Override
        public void nextInstructions(ClientRequest request, StreamObserver<Instruction> responseObserver) {
            int idx = 1;

            {
                int id = 1;
                var instruction = Instruction.newBuilder()
                        .setTextContent("[%d] %d".formatted(id, idx))
                        .setElementId(id)
                        .setType(Instruction.Type.CREATE)
                        .build();
                responseObserver.onNext(instruction);
            }

            {
                int id = 2;
                var instruction = Instruction.newBuilder()
                        .setTextContent("[%d] %d".formatted(id, idx))
                        .setElementId(id)
                        .setType(Instruction.Type.CREATE)
                        .build();
                responseObserver.onNext(instruction);
            }

            while (idx <= NUMBER_OF_INSTRUCTIONS) {
                try {
                    Thread.sleep(INSTRUCTION_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                idx++;

                {
                    int id = 1;
                    var instruction = Instruction.newBuilder()
                            .setTextContent("[%d] %d".formatted(id, idx))
                            .setElementId(id)
                            .setType(Instruction.Type.UPDATE)
                            .build();
                    responseObserver.onNext(instruction);
                }

                {
                    int id = 2;
                    var instruction = Instruction.newBuilder()
                            .setTextContent("[%d] %d".formatted(id, idx))
                            .setElementId(id)
                            .setType(Instruction.Type.UPDATE)
                            .build();
                    responseObserver.onNext(instruction);
                }
            }

            try {
                Thread.sleep(INSTRUCTION_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            {
                int id = 1;
                var instruction = Instruction.newBuilder()
                        .setElementId(id)
                        .setType(Instruction.Type.DELETE)
                        .build();
                responseObserver.onNext(instruction);
            }

            {
                int id = 2;
                var instruction = Instruction.newBuilder()
                        .setElementId(id)
                        .setType(Instruction.Type.DELETE)
                        .build();
                responseObserver.onNext(instruction);
            }

            responseObserver.onCompleted();
        }
    }
}
