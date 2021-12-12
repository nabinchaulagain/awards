package rltw.awards.soldier.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import rltw.awards.error.model.NotFoundException;
import rltw.awards.soldier.constants.SoldierConstants;
import rltw.awards.soldier.model.Soldier;
import rltw.awards.soldier.repository.SoldierRepository;


import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class SoldierService {
    @Inject
    private SoldierRepository soldierRepository;

    @Transactional
    public Soldier add(Soldier soldier) {
        return soldierRepository.save(soldier);
    }

    @Transactional
    public Soldier edit(long id, Soldier editedSoldier) {
        var soldier = this.get(id);

        soldier.setBirthplace(editedSoldier.getBirthplace());
        soldier.setName(editedSoldier.getName());
        soldier.setDeathplace(editedSoldier.getDeathplace());
        soldier.setDateOfBirth(editedSoldier.getDateOfBirth());
        soldier.setDateOfDeath(editedSoldier.getDateOfDeath());
        soldier.setUnit(editedSoldier.getUnit());
        soldier.setServiceEndDate(editedSoldier.getServiceEndDate());
        soldier.setServiceStartDate(editedSoldier.getServiceStartDate());
        soldier.setRank(editedSoldier.getRank());

        return soldier;
    }

    @Transactional
    public void remove(long id) {
        var soldier = this.get(id);

        soldierRepository.delete(soldier);
    }

    public Soldier get(long id) {
        return soldierRepository.findById(id).orElseThrow(() -> new NotFoundException(SoldierConstants.SOLDIER_NOT_FOUND));
    }

    public List<Soldier> getAll() {
        return (List<Soldier>) soldierRepository.findAll();
    }
}
