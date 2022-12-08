package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseServiceImpl;
import ir.maktab.entity.comment.Comment;
import ir.maktab.entity.order.Order;
import ir.maktab.entity.order.OrderStatus;
import ir.maktab.entity.person.Customer;
import ir.maktab.entity.service.MainService;
import ir.maktab.entity.service.SubService;
import ir.maktab.exception.DuplicateEmailException;
import ir.maktab.exception.DuplicateMobileException;
import ir.maktab.exception.OrderIsNotExistException;
import ir.maktab.exception.SubServiceIsNotExistException;
import ir.maktab.repository.CustomerRepository;
import ir.maktab.service.CustomerService;
import ir.maktab.util.AppContext;
import ir.maktab.util.Validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl
        extends BaseServiceImpl<Customer, Long, CustomerRepository>
        implements CustomerService {

    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }

    @Override
    public void signUp(Customer customer) {
        if (findByPhoneNumber(customer.getPhoneNumber()).isPresent())
            throw new DuplicateMobileException("this mobile number already exist!");
        else if (findByEmail(customer.getEmail()).isPresent())
            throw new DuplicateEmailException("this email number already exist!");
        Validation.checkPhoneNumberAndEmailAndPassword(customer);
        customer.setCredit(0L);
        saveOrUpdate(customer);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        try {
            return repository.findByEmail(email);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void editPassword(Customer customer, String newPassword) {
        try {
            repository.getEntityManager().getTransaction().begin();
            repository.editPassword(customer, newPassword);
            repository.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            repository.getEntityManager().getTransaction().rollback();
        }
    }

    @Override
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        try {
            return repository.findByPhoneNumber(phoneNumber);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<MainService> findAllMainService() {
        return AppContext.getMainServiceService().findAll();
    }

    @Override
    public List<SubService> findAllSubService() {
        return AppContext.getSubServiceService().findAll();
    }

    @Override
    public void addOrder(Customer customer, String subServiceName, Long proposedPrice,
                         String description, LocalDate date, LocalTime time, String address) {
        Optional<SubService> subService = AppContext.getSubServiceService().findByName(subServiceName);
        if (subService.isEmpty()) {
            throw new SubServiceIsNotExistException("this sub-service dose not exist!");
        }
        Order order = new Order(proposedPrice, description, LocalDateTime.of(date, time),
                address, OrderStatus.WAITING_FOR_EXPERT_SUGGESTION, customer, subService.get());
        AppContext.getOrderService().saveOrUpdate(order);
    }

    @Override
    public void addCommentForExpertPerformance(Comment comment, Long orderId) {
        if (AppContext.getOrderService().findById(orderId).isEmpty()) {
            throw new OrderIsNotExistException("there is no order!");
        }
        AppContext.getCommentService().saveOrUpdate(comment);
    }
}
