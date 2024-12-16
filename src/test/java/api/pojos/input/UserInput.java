package api.pojos.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {

    private int id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phone;

    private int userStatus;
}
