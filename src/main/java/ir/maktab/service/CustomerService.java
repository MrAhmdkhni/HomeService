package ir.maktab.service;

import ir.maktab.base.service.BaseService;
import ir.maktab.entity.comment.Comment;
import ir.maktab.entity.person.Customer;
import ir.maktab.entity.service.MainService;
import ir.maktab.entity.service.SubService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CustomerService extends BaseService<Customer, Long> {

    void signUp(Customer customer);

    Optional<Customer> findByEmail(String email);

    void editPassword(Customer customer, String newPassword);

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    List<MainService> findAllMainService();

    List<SubService> findAllSubService();

    void addOrder(Customer customer, String subServiceName, Long proposedPrice,
                  String description, LocalDate date, LocalTime time, String address);

    void addCommentForExpertPerformance(Comment comment, Long orderId);
}
