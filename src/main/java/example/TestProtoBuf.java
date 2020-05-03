package example;

import proto.user.UserDefProto;

import java.util.Random;
import java.util.stream.IntStream;

public class TestProtoBuf {

    private static UserDefProto.UserDefinitionList userDefinitions;

    static void readUser(int userId) {

        // inInitialized denotes if all the required fields have been set or not
        if (userDefinitions != null && userDefinitions.isInitialized()) {
            UserDefProto.UserDefinition userDefinition = userDefinitions.getUserDefinitions(userId);
            System.out.println(userDefinition != null ? userDefinition.toString() : "USER_NOT_FOUND");
        }

    }

    static void writeUser() {

        UserDefProto.UserDefinitionList.Builder listBuilder = UserDefProto.UserDefinitionList.newBuilder();

        IntStream.range(0, 10).forEach(i -> {

            UserDefProto.UserDefinition.Builder userDefinitionBuilder
                    = UserDefProto.UserDefinition.newBuilder();

            userDefinitionBuilder.setUserName("user_name_" + i);
            userDefinitionBuilder.setId(i);

            if (i % 4 == 0) {
                userDefinitionBuilder.setEmail(i + "_email@email.com");
            }

            if (i % 3 == 0) {
                userDefinitionBuilder.setPhoneNum("9876543210");
            }

            UserDefProto.UserDefinition definition = userDefinitionBuilder.build();

            listBuilder.addUserDefinitions(definition);

        });

        userDefinitions = listBuilder.build();
    }

    public static void main(String[] args) {
        System.out.println("java.version: " + System.getProperty("java.version"));

        writeUser();

        readUser(new Random().nextInt(10));
    }

}
