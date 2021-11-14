package rltw.awards.auth;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import io.reactivex.Flowable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import rltw.awards.auth.model.RefreshToken;
import rltw.awards.auth.model.Role;
import rltw.awards.auth.model.User;
import rltw.awards.auth.service.AuthService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class CustomRefreshTokenPersistence implements RefreshTokenPersistence {
    @Inject
    private AuthService authService;

    @Override
    public void persistToken(RefreshTokenGeneratedEvent event) {
        var refreshToken = event.getRefreshToken();
        var authentication = event.getAuthentication();
        long userId = (long) authentication.getAttributes().get("id");

        authService.saveRefreshToken(refreshToken, userId);
    }

    @Override
    public Publisher<Authentication> getAuthentication(String token) {
        return Flowable.just(getAuthenticationFlowable(token));
    }

    @Transactional
    public Authentication getAuthenticationFlowable(String token) {
        RefreshToken refreshToken = authService.getRefreshToken(token);

        var currDate = new Date();
        if (refreshToken.getExpiryDate().before(currDate)) {
            throw new AuthorizationException(null);
        }

        User user = authService.getUserById(refreshToken.getUserId());

        List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        Map<String, Object> userProps = new HashMap<>();
        userProps.put("username", user.getUsername());
        userProps.put("email", user.getEmail());
        userProps.put("id", user.getId());

        return Authentication.build(user.getUsername(), roles, userProps);
    }
}
