package service;

import example.UserDefRepository;
import proto.user.UserDefProto;
import proto.user.UserGrpc;

public class UserService extends UserGrpc.UserImplBase {

    @Override
    public void readUser(proto.user.UserDefProto.UserRequest request,
                         io.grpc.stub.StreamObserver<proto.user.UserDefProto.UserDefinition> responseObserver) {

        int id = request.getId();

        UserDefProto.UserDefinition definition = UserDefRepository.readUser(id);

        // prepping response
        responseObserver.onNext(definition);
        responseObserver.onCompleted();

    }

}
