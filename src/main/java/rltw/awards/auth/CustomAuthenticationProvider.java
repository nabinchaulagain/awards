package rltw.awards.auth;

import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.Flowable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import rltw.awards.auth.constant.AuthConstants;
import rltw.awards.auth.model.User;
import rltw.awards.auth.service.AuthService;
import rltw.awards.error.model.BadPayloadException;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Inject
    AuthService authService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        String username = (String) authenticationRequest.getIdentity();
        String password = (String) authenticationRequest.getSecret();


        return Flowable.just(getAuthenticationResponseFlowable(username, password));
    }

    @Transactional
    public AuthenticationResponse getAuthenticationResponseFlowable(String username, String password) {
        User user = authService.getUserByUsername(username);

        if (!authService.doesPasswordMatchWithHash(user.getPassword(), password)) {
            throw new BadPayloadException(CollectionUtils.mapOf("message", AuthConstants.CREDENTIALS_INVALID, "fieldErrors", CollectionUtils.mapOf("password", AuthConstants.PASSWORD_NO_MATCH)));
        }
        // TODO: work on roles.
        List<String> roles = user.getRoles().stream().map((role) -> role.getName()).collect(Collectors.toList());
        Map<String, Object> userProps = new HashMap<>();
        userProps.put("username", user.getUsername());
        userProps.put("email", user.getEmail());
        userProps.put("id", user.getId());

        return (AuthenticationResponse.success(username, roles, userProps));
    }
}
