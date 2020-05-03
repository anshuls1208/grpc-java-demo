import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.UserService;

import java.io.IOException;

public class Application {

    private static final int SERVER_PORT = 8091;

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Starting gRPC server at port=" + SERVER_PORT);

        Server server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(new UserService())
                .build();

        server.start();

        System.out.println("Server started at port=" + server.getPort());

        server.awaitTermination();
    }

}
