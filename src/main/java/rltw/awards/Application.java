package rltw.awards;

import io.micronaut.openapi.annotation.OpenAPIInclude;
import io.micronaut.runtime.Micronaut;
import io.micronaut.security.endpoints.LoginController;
import io.micronaut.security.token.jwt.endpoints.OauthController;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import rltw.awards.auth.constant.AuthConstants;

@OpenAPIDefinition(
        info = @Info(
                title = "awards",
                version = "1.0",
                description = "Awards API"
        ),
        servers = @Server(url = "/api/v1")
)
@SecurityScheme(name = AuthConstants.SECURITY_SCHEME_NAME,
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "jwt")
@OpenAPIInclude(classes = {LoginController.class, OauthController.class}, tags = {@Tag(name = "Auth")})
public class Application {

    public static void main(String[] args) {
        Micronaut.run();
    }
}
