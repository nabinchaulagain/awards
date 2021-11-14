package rltw.awards.soldier.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import rltw.awards.soldier.model.Soldier;

@Repository
public interface SoldierRepository extends CrudRepository<Soldier, Long> {
}
