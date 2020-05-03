package example;

import proto.user.UserDefProto;

import java.util.stream.IntStream;

public class UserDefRepository {

    private static UserDefProto.UserDefinitionList userDefinitions;

    static {
        writeUser();
    }

    public static UserDefProto.UserDefinition readUser(int userId) {

        UserDefProto.UserDefinition userDefinition = null;

        // inInitialized denotes if all the required fields have been set or not
        if (userDefinitions != null && userDefinitions.isInitialized()) {
            userDefinition = userDefinitions.getUserDefinitions(userId);
            System.out.println(userDefinition != null ? userDefinition.toString() : "USER_NOT_FOUND");
        }

        return userDefinition;

    }

    public static void writeUser() {

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

}
