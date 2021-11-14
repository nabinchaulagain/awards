package rltw.awards.auth.constant;

public class AuthConstants {
    public static final String USERNAME_REQUIRED = "Username is required";
    public static final String USERNAME_LEN_VALIDATION_MSG = "Username must be between 3-40 characters long";
    public static final String USERNAME_NOT_FOUND = "Username doesn't exist";
    public static final String USERNAME_DUPLICATE = "Username is already taken";

    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String PASSWORD_LEN_VALIDATION_MSG = "Password must be between 5-40 characters long";

    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String INVALID_EMAIL = "Email is invalid";
    public static final String EMAIL_DUPLICATE = "Email is already taken";

    public static final String CREDENTIALS_INVALID = "Invalid credentials";
    public static final String PASSWORD_NO_MATCH = "Password didn't match";

    public static final int REFRESH_TOKEN_EXPIRY_DAYS = 30;

    public static final String USER_NOT_FOUND = "User not found.";
    public static final String REFRESH_TOKEN_NOT_FOUND = "Refresh token not found";

    public static final String SECURITY_SCHEME_NAME = "Bearer JWT token";
}
