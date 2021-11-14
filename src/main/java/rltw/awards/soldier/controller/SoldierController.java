package rltw.awards.soldier.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import rltw.awards.auth.constant.AuthConstants;
import rltw.awards.common.constants.Roles;
import rltw.awards.soldier.constants.SoldierConstants;
import rltw.awards.soldier.model.Soldier;
import rltw.awards.soldier.service.SoldierService;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Soldiers")
@Controller("/soldiers")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class SoldierController {
    @Inject
    private SoldierService soldierService;

    @Get
    public List<Soldier> getAll() {
        return soldierService.getAll();
    }

    @Post
    @Secured(Roles.ADMIN)
    @SecurityRequirement(name = AuthConstants.SECURITY_SCHEME_NAME)
    public Soldier add(@Body @Valid Soldier payload) {
        return soldierService.add(payload);
    }

    @Get("/{id}")
    public Soldier get(@PathVariable("id") long id) {
        return soldierService.get(id);
    }

    @Patch("/{id}")
    @Secured(Roles.ADMIN)
    @SecurityRequirement(name = AuthConstants.SECURITY_SCHEME_NAME)
    public Soldier edit(@PathVariable("id") long id, @Body @Valid Soldier payload) {
        return soldierService.edit(id, payload);
    }

    @Delete("/{id}")
    @Secured(Roles.ADMIN)
    @SecurityRequirement(name = AuthConstants.SECURITY_SCHEME_NAME)
    public HttpResponse delete(@PathVariable("id") long id) {
        soldierService.remove(id);

        return HttpResponse.ok(SoldierConstants.SOLDIER_DELETED);
    }
}
