package rltw.awards.error.handler;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import rltw.awards.error.model.BadPayloadException;

@Singleton
public class BadPayloadExceptionHandler implements ExceptionHandler<BadPayloadException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, BadPayloadException exception) {
        return HttpResponse.badRequest(exception.getResponse());
    }
}
