package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entity.person.Customer;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    void editPassword(Customer customer, String newPassword);

    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
