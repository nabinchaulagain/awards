package rltw.awards.unit.repository;

import java.util.List;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import rltw.awards.unit.model.Unit;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {

    public Iterable<Unit> listOrderByIdDesc();
}
