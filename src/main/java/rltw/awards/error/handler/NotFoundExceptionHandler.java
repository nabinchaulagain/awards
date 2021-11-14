package rltw.awards.error.handler;

import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import rltw.awards.error.model.NotFoundException;

@Singleton
public class NotFoundExceptionHandler implements ExceptionHandler<NotFoundException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, NotFoundException exception) {
        return HttpResponse.notFound(CollectionUtils.mapOf("message", exception.getMessage()));
    }
}
