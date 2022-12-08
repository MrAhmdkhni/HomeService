package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entity.service.MainService;
import ir.maktab.entity.service.SubService;
import ir.maktab.repository.SubServiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class SubServiceRepositoryImpl extends BaseRepositoryImpl<SubService, Long> implements SubServiceRepository {

    public SubServiceRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<SubService> getEntityClass() {
        return SubService.class;
    }

    @Override
    public Optional<SubService> findByName(String name) {
        String hql = """
                select ss from SubService ss
                where ss.name = :name
                """;
        TypedQuery<SubService> query = em.createQuery(hql, SubService.class);
        return Optional.ofNullable(
                query.setParameter("name", name).getSingleResult()
        );
    }

    @Override
    public void deleteByName(String name) {
        String hql = """
                delete from SubService ss
                where ss.name = :name
                """;
        em.createQuery(hql)
                .setParameter("name", name)
                .executeUpdate();
    }

    @Override
    public void editBasePriceAndDescription(String subServiceName, Long basePrice, String description) {
        String hql = """
                update SubService ss
                set ss.basePrice = :basePrice, ss.description = :description
                where ss.name = :name
                """;
        em.createQuery(hql)
                .setParameter("basePrice", basePrice)
                .setParameter("description", description)
                .setParameter("name", subServiceName)
                .executeUpdate();
    }
}
