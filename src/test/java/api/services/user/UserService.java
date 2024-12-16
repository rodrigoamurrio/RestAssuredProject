package api.services.user;

import api.base.BaseService;
import api.pojos.input.UserInput;
import io.restassured.response.Response;

import java.util.HashMap;

public class UserService extends BaseService {

    private static final String BASE_PATH = "api/v3/";

    public Response createUser(UserInput user) {
        return postRequest(user, BASE_PATH + "user");
    }

    public Response getUserByUserName(String userName) {
        HashMap<String, String> pathParameter = new HashMap<>();
        pathParameter.put("username", userName);
        return getRequest(pathParameter, BASE_PATH + "user/{username}");
    }

    public Response updateUserByUserName(String userName, UserInput user) {
        HashMap<String, String> pathParameter = new HashMap<>();
        pathParameter.put("username", userName);
        return putRequest(pathParameter, user, BASE_PATH + "user/{username}");
    }

    public Response deleteUserByUserName(String userName) {
        HashMap<String, String> pathParameter = new HashMap<>();
        pathParameter.put("username", userName);
        return deleteRequest(pathParameter, BASE_PATH + "user/{username}");
    }


}
