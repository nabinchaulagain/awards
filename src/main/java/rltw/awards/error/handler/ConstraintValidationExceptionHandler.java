package rltw.awards.error.handler;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.validation.exceptions.ConstraintExceptionHandler;
import jakarta.inject.Singleton;
import rltw.awards.common.constants.Constants;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Replaces(ConstraintExceptionHandler.class)
public class ConstraintValidationExceptionHandler implements ExceptionHandler<ConstraintViolationException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();
        if (!exception.getConstraintViolations().isEmpty()) {
            for (ConstraintViolation constraintViolation : exception.getConstraintViolations()) {
                String fieldName = null;
                for (Path.Node node : constraintViolation.getPropertyPath()) {
                    fieldName = node.getName();
                }
                errors.put(fieldName, constraintViolation.getMessage());
            }
        }

        return HttpResponse.badRequest(CollectionUtils.mapOf("message", Constants.INVALID_PAYLOAD, "fieldErrors", errors));
    }
}
