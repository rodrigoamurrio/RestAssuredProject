package api.tests;

import api.pojos.input.UserInput;
import api.pojos.response.UserResponse;
import api.services.user.UserService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static constant.userConstant.userConstant.*;


public class UserServiceTest {

    UserInput userInput = new UserInput();
    UserService userService = new UserService();


    @Test
    public void createUserTest() {

        userInput.setId(USER_ID);
        userInput.setUsername(USER_NAME);
        userInput.setFirstName(USER_FIRST_NAME);
        userInput.setLastName(USER_LAST_NAME);
        userInput.setEmail(USER_EMAIL);
        userInput.setPassword(USER_PASSWORD);
        userInput.setPhone(USER_PHONE_NUMBER);
        userInput.setUserStatus(USER_STATUS);

        Response res = userService.createUser(userInput);
        Assert.assertEquals(res.statusCode(), 200, "The status code is not success");

        UserResponse userResponse = res.as(UserResponse.class);

        Assert.assertEquals(userInput.getId(), userResponse.getId(), "The id is not equals to entered");
        Assert.assertEquals(userInput.getUsername(), userResponse.getUsername(), "The username is not equals to entered");
        Assert.assertEquals(userInput.getFirstName(), userResponse.getFirstName(), "The first name is not equals to entered");
        Assert.assertEquals(userInput.getLastName(), userResponse.getLastName(), "The last name is not equals to entered");
        Assert.assertEquals(userInput.getPassword(), userResponse.getPassword(), "The password equals to entered");
        Assert.assertEquals(userInput.getEmail(), userResponse.getEmail(), "The email not equals to entered");
        Assert.assertEquals(userInput.getPhone(), userResponse.getPhone(), "The phone number is not equals to entered");
        Assert.assertEquals(userInput.getUserStatus(), userResponse.getUserStatus(), "The user status is not equals to entered");
    }

    @Test
    public void getUserByUserNameTest() {

        Response res = userService.getUserByUserName(USER_NAME);
        Assert.assertEquals(res.statusCode(), 200, "The status code is not success");

        UserResponse userResponse = res.as(UserResponse.class);

        Assert.assertEquals(userResponse.getId(), USER_ID, "The id is not equals to entered");
        Assert.assertEquals(userResponse.getUsername(), USER_NAME, "The username is not equals to entered");
        Assert.assertEquals(userResponse.getFirstName(), USER_FIRST_NAME, "The first name is not equals to entered");
        Assert.assertEquals(userResponse.getLastName(), USER_LAST_NAME, "The last name is not equals to entered");
        Assert.assertEquals(userResponse.getPassword(), USER_PASSWORD, "The password equals to entered");
        Assert.assertEquals(userResponse.getEmail(), USER_EMAIL, "The email not equals to entered");
        Assert.assertEquals(userResponse.getPhone(), USER_PHONE_NUMBER, "The phone number is not equals to entered");
        Assert.assertEquals(userResponse.getUserStatus(), USER_STATUS, "The user status is not equals to entered");

    }

    @Test
    public void modifyEmailUserByUserName() {

        userInput.setId(USER_ID);
        userInput.setUsername(USER_NAME);
        userInput.setFirstName(USER_FIRST_NAME);
        userInput.setLastName(USER_LAST_NAME);
        userInput.setEmail(USER_EMAIL_UPDATED);
        userInput.setPassword(USER_PASSWORD);
        userInput.setPhone(USER_PHONE_NUMBER);
        userInput.setUserStatus(USER_STATUS);

        Response resUpdate = userService.updateUserByUserName(USER_NAME, userInput);
        Assert.assertEquals(resUpdate.statusCode(), 200, "The status code is not success");

        Response resGet = userService.getUserByUserName(USER_NAME);
        Assert.assertEquals(resUpdate.statusCode(), 200, "The status code is not success");

        UserResponse userResponse = resGet.as(UserResponse.class);

        Assert.assertEquals(userResponse.getEmail(), USER_EMAIL_UPDATED, "The email cannot be updated");
    }

    @Test
    public void removeUserByUserName() {

        Response resDelete = userService.deleteUserByUserName(USER_NAME);
        Assert.assertEquals(resDelete.statusCode(), 200, "The status code is not success");

        Response resGet = userService.getUserByUserName(USER_NAME);
        Assert.assertEquals(resGet.statusCode(), 404, "The status should be 404 because the user was deleted");
        Assert.assertEquals(resGet.getBody().print(), "User not found", "The error message should be user not found");
    }

}
