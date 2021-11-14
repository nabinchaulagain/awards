package rltw.awards.auth.service;

import io.micronaut.core.util.CollectionUtils;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.mindrot.jbcrypt.BCrypt;
import rltw.awards.auth.constant.AuthConstants;
import rltw.awards.auth.model.RefreshToken;
import rltw.awards.auth.model.User;
import rltw.awards.auth.repository.RefreshTokenRepository;
import rltw.awards.auth.repository.UserRepository;
import rltw.awards.common.constants.Constants;
import rltw.awards.error.model.BadPayloadException;
import rltw.awards.error.model.NotFoundException;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class AuthService {
    @Inject
    private UserRepository userRepository;

    @Inject
    private RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public String hashPassword(String password) {
        var salt = BCrypt.gensalt();
        var hashedPassword = BCrypt.hashpw(password, salt);

        return hashedPassword;
    }

    public boolean doesPasswordMatchWithHash(String hashedPassword, String password) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public void validateDuplicateUsernameAndEmail(String username, String email) {
        boolean hasError = false;
        Map<String, Object> error = new HashMap<>();


        if (userRepository.existsByEmail(email)) {
            error.put("email", AuthConstants.EMAIL_DUPLICATE);
            hasError = true;
        }
        if (userRepository.existsByUsername(username)) {
            error.put("username", AuthConstants.USERNAME_DUPLICATE);
            hasError = true;
        }

        if (hasError) {
            throw new BadPayloadException(CollectionUtils.mapOf("fieldErrors", error, "message", Constants.INVALID_PAYLOAD));
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username).orElseThrow(() -> new BadPayloadException(
                CollectionUtils.mapOf(
                        "message", AuthConstants.CREDENTIALS_INVALID,
                        "fieldErrors", CollectionUtils.mapOf(
                                "username",
                                AuthConstants.USERNAME_NOT_FOUND)))
        );
    }

    @Transactional
    public RefreshToken saveRefreshToken(String token, long userId) {
        var refreshToken = new RefreshToken();

        var currentDate = new Date();

        var calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, AuthConstants.REFRESH_TOKEN_EXPIRY_DAYS);
        var expiryDate = calendar.getTime();

        refreshToken.setToken(token);
        refreshToken.setUserId(userId);
        refreshToken.setExpiryDate(expiryDate);
        refreshToken.setIssuedDate(currentDate);

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken getRefreshToken(String token) {
        return refreshTokenRepository.findById(token).orElseThrow(() -> new NotFoundException(AuthConstants.REFRESH_TOKEN_NOT_FOUND));
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(AuthConstants.USER_NOT_FOUND));
    }
}
