package rltw.awards.soldier.service;

import jakarta.inject.Singleton;
import rltw.awards.common.model.ListResponse;
import rltw.awards.soldier.model.Soldier;
import rltw.awards.soldier.model.SoldierQueryParams;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.JoinType;
import java.util.Optional;

@Singleton
public class SoldierCriteriaBuilder {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Soldier> findById(long id) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Soldier.class);
        var root = criteriaQuery.from(Soldier.class);
        root.fetch("unit", JoinType.LEFT);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

        try {
            return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
        } catch (NoResultException exception) {
            return Optional.empty();
        }
    }

    public ListResponse<Soldier> findAll(SoldierQueryParams queryParams) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Soldier.class);
        var root = criteriaQuery.from(Soldier.class);

        var sizeQuery = criteriaBuilder.createQuery(Long.class);
        sizeQuery.select(criteriaBuilder.count(sizeQuery.from(Soldier.class)));
        var size = entityManager.createQuery(sizeQuery).getSingleResult();

        root.fetch("unit", JoinType.LEFT);

        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
        var typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((queryParams.getPageNumber() - 1) * queryParams.getPageSize());
        typedQuery.setMaxResults(queryParams.getPageSize());

        return new ListResponse<>(typedQuery.getResultList(), size);
    }
}
