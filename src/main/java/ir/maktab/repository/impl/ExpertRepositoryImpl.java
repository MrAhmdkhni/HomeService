package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entity.person.Expert;
import ir.maktab.entity.person.ExpertStatus;
import ir.maktab.repository.ExpertRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class ExpertRepositoryImpl extends BaseRepositoryImpl<Expert, Long> implements ExpertRepository {

    public ExpertRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Expert> getEntityClass() {
        return Expert.class;
    }

    @Override
    public Optional<Expert> findByEmail(String email) {
        String hql = """
                select e from Expert e
                where e.email = :email
                """;
        TypedQuery<Expert> query = em.createQuery(hql, Expert.class);
        return Optional.ofNullable(
                query.setParameter("email", email).getSingleResult()
        );
    }

    @Override
    public void editPassword(Expert expert, String newPassword) {
        String hql = """
                update Expert e
                set e.password = :password
                where e.email = :email
                """;
        em.createQuery(hql)
                .setParameter("password", newPassword)
                .setParameter("email", expert.getEmail())
                .executeUpdate();
    }

    @Override
    public Optional<Expert> findByPhoneNumber(String phoneNumber) {
        String hql = """
                select e from Expert e
                where e.phoneNumber = :phoneNumber
                """;
        TypedQuery<Expert> query = em.createQuery(hql, Expert.class);
        return Optional.ofNullable(
                query.setParameter("phoneNumber", phoneNumber).getSingleResult()
        );
    }

    @Override
    public void editExpertStatus(ExpertStatus expertStatus, String phoneNumber) {
        String hql = """
                update Expert e
                set e.expertStatus = :expertStatus
                where e.phoneNumber = :phoneNumber
                """;
        em.createQuery(hql)
                .setParameter("expertStatus", expertStatus)
                .setParameter("phoneNumber", phoneNumber)
                .executeUpdate();
    }
}
