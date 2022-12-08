package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entity.person.Customer;
import ir.maktab.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer, Long> implements CustomerRepository {

    public CustomerRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        String hql = """
                select c from Customer c
                where c.email = :email
                """;
        TypedQuery<Customer> query = em.createQuery(hql, Customer.class);
        return Optional.ofNullable(
                query.setParameter("email", email).getSingleResult()
        );
    }

    @Override
    public void editPassword(Customer customer, String newPassword) {
        String hql = """
                update Customer c
                set c.password = :password
                where c.email = :email
                """;
        em.createQuery(hql)
                .setParameter("password", newPassword)
                .setParameter("email", customer.getEmail())
                .executeUpdate();
    }

    @Override
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        String hql = """
                select c from Customer c
                where c.phoneNumber = :phoneNumber
                """;
        TypedQuery<Customer> query = em.createQuery(hql, Customer.class);
        return Optional.ofNullable(
                query.setParameter("phoneNumber", phoneNumber).getSingleResult()
        );
    }
}
