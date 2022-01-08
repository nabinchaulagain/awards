package rltw.awards.unit.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import rltw.awards.auth.constant.AuthConstants;
import rltw.awards.common.constants.Roles;
import rltw.awards.common.util.S3Service;
import rltw.awards.unit.constant.UnitConstants;
import rltw.awards.unit.model.Unit;
import rltw.awards.unit.service.UnitService;

import javax.validation.Valid;
import java.util.List;


@Tag(name = "Units")
@Controller("/units")
@Secured(SecurityRule.IS_ANONYMOUS)
public class UnitController {
    @Inject
    private S3Service s3Service;

    @Inject
    private UnitService unitService;

    @Get
    public List<Unit> fetchAll() {
        return unitService.getAll();
    }

    @Get("/{id}")
    public Unit get(@PathVariable("id") long id) {
        return unitService.getUnit(id);
    }

    @Post
    @Secured(Roles.ADMIN)
    @SecurityRequirement(name = AuthConstants.SECURITY_SCHEME_NAME)
    public Unit add(@Body @Valid Unit unit) {
        return unitService.add(unit);
    }

    @Delete("/{id}")
    @Secured(Roles.ADMIN)
    @SecurityRequirement(name = AuthConstants.SECURITY_SCHEME_NAME)
    public HttpResponse delete(@PathVariable("id") long id) {
        unitService.deleteUnit(id);

        return HttpResponse.ok(UnitConstants.UNIT_CREATED);
    }

    @Patch("/{id}")
    @Secured(Roles.ADMIN)
    @SecurityRequirement(name = AuthConstants.SECURITY_SCHEME_NAME)
    public Unit edit(@PathVariable("id") long id, @Body @Valid Unit payload) {
        return unitService.editUnit(id, payload);
    }
}
