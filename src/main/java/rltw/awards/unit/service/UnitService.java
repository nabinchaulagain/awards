package rltw.awards.unit.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import rltw.awards.error.model.NotFoundException;
import rltw.awards.unit.constant.UnitConstants;
import rltw.awards.unit.model.Unit;
import rltw.awards.unit.repository.UnitRepository;

import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class UnitService {
    @Inject
    UnitRepository unitRepository;

    public List<Unit> getAll() {
        return (List<Unit>) unitRepository.findAll();
    }

    public Unit getUnit(long id) {
        return unitRepository.findById(id).orElseThrow(() -> new NotFoundException(UnitConstants.UNIT_NOT_FOUND));
    }

    @Transactional
    public Unit add(Unit unit) {
        return unitRepository.save(unit);
    }

    @Transactional
    public void deleteUnit(long id) {
        var unit = this.getUnit(id);

        unitRepository.delete(unit);
    }

    @Transactional
    public Unit editUnit(long id, Unit updatedUnit) {
        var unit = this.getUnit(id);

        unit.setName(updatedUnit.getName());
        unit.setBranch(updatedUnit.getBranch());
        unit.setCountry(updatedUnit.getCountry());
        unit.setEstablishedDate(updatedUnit.getEstablishedDate());

        return unit;
    }
}
