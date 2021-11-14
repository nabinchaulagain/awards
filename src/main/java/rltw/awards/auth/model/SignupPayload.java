package rltw.awards.auth.model;

import io.micronaut.core.annotation.Introspected;
import rltw.awards.auth.constant.AuthConstants;

import javax.validation.constraints.*;

@Introspected
public class SignupPayload {
    @NotEmpty(message = AuthConstants.USERNAME_REQUIRED)
    @Size(min = 3, max = 40, message = AuthConstants.USERNAME_LEN_VALIDATION_MSG)
    private String username;

    @NotEmpty(message = AuthConstants.EMAIL_REQUIRED)
    @Email(message = AuthConstants.INVALID_EMAIL)
    private String email;

    @NotEmpty(message = AuthConstants.PASSWORD_REQUIRED)
    @Size(min = 5, max = 40, message = AuthConstants.PASSWORD_LEN_VALIDATION_MSG)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser() {
        var user = new User();
        user.setEmail(this.getEmail());
        user.setUsername(this.getUsername());
        user.setPassword((this.getPassword()));

        return user;
    }
}
