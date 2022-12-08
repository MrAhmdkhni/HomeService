package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entity.service.MainService;
import ir.maktab.repository.MainServiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class MainServiceRepositoryImpl extends BaseRepositoryImpl<MainService, Long> implements MainServiceRepository {

    public MainServiceRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<MainService> getEntityClass() {
        return MainService.class;
    }

    @Override
    public Optional<MainService> findByName(String name) {
        String hql = """
                select ms from MainService ms
                where ms.name = :name
                """;
        TypedQuery<MainService> query = em.createQuery(hql, MainService.class);
        return Optional.ofNullable(
                query.setParameter("name", name).getSingleResult()
        );
    }

    @Override
    public void deleteByName(String name){
        String hql = """
                delete from MainService ms
                where ms.name = :name
                """;
        em.createQuery(hql)
                .setParameter("name", name)
                .executeUpdate();
    }
}
