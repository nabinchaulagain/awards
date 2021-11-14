package rltw.awards.auth.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import rltw.awards.auth.model.SignupPayload;
import rltw.awards.auth.model.User;
import rltw.awards.auth.service.AuthService;

import javax.validation.Valid;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/auth")
@Tag(name = "Auth")
public class AuthController {
    @Inject
    private AuthService authService;

    @Post("/signup")
    User signup(@Body @Valid SignupPayload signupPayload) {
        authService.validateDuplicateUsernameAndEmail(signupPayload.getUsername(), signupPayload.getEmail());
        signupPayload.setPassword(authService.hashPassword(signupPayload.getPassword()));

        return authService.createUser(signupPayload.toUser());
    }
}
