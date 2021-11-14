package rltw.awards.error.handler;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.authentication.DefaultAuthorizationExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Replaces(DefaultAuthorizationExceptionHandler.class)
public class UnauthenticatedExceptionHandler implements ExceptionHandler<AuthorizationException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, AuthorizationException exception) {
        var response = CollectionUtils.mapOf("message", exception.isForbidden() ? "Action not allowed." : "You are not logged in.");
        var status = exception.isForbidden() ? HttpStatus.FORBIDDEN : HttpStatus.UNAUTHORIZED;

        return HttpResponse.status(status).body(response);
    }
}
