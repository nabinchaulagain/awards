package rltw.awards.soldier.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.annotations.Nullable;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import rltw.awards.auth.constant.AuthConstants;
import rltw.awards.common.constants.Roles;
import rltw.awards.common.model.ListResponse;
import rltw.awards.soldier.constants.SoldierConstants;
import rltw.awards.soldier.model.Soldier;
import rltw.awards.soldier.model.SoldierQueryParams;
import rltw.awards.soldier.service.SoldierService;

import javax.validation.Valid;

@Tag(name = "Soldiers")
@Controller("/soldiers")
@Secured(SecurityRule.IS_ANONYMOUS)
public class SoldierController {
    @Inject
    private SoldierService soldierService;

    @Get("{?filterParams*}")
    public HttpResponse<ListResponse<Soldier>> getAll(@Nullable SoldierQueryParams filterParams) {
        return HttpResponse.ok(soldierService.getAll(filterParams));
    }

    @Post
    @Secured(Roles.ADMIN)
    @SecurityRequirement(name = AuthConstants.SECURITY_SCHEME_NAME)
    public HttpResponse<Soldier> add(@Body @Valid Soldier payload) {
        return HttpResponse.created(soldierService.add(payload));
    }

    @Get("/{id}")
    public HttpResponse<Soldier> get(@PathVariable("id") long id) {
        return HttpResponse.ok(soldierService.get(id));
    }

    @Patch("/{id}")
    @Secured(Roles.ADMIN)
    @SecurityRequirement(name = AuthConstants.SECURITY_SCHEME_NAME)
    public HttpResponse<Soldier> edit(@PathVariable("id") long id, @Body @Valid Soldier payload) {
        return HttpResponse.ok(soldierService.edit(id, payload));
    }

    @Delete("/{id}")
    @Secured(Roles.ADMIN)
    @SecurityRequirement(name = AuthConstants.SECURITY_SCHEME_NAME)
    public HttpResponse delete(@PathVariable("id") long id) {
        soldierService.remove(id);

        return HttpResponse.ok(SoldierConstants.SOLDIER_DELETED);
    }

    @Get("/test")
    public HttpResponse test() {
        return HttpResponse.ok("Hope it's REdeployed");
    }
}
