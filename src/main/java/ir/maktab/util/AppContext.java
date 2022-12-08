package ir.maktab.util;

import ir.maktab.repository.*;
import ir.maktab.repository.impl.*;
import ir.maktab.service.*;
import ir.maktab.service.impl.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AppContext {

    private final static AdminRepository ADMIN_REPOSITORY;
    private final static CommentRepository COMMENT_REPOSITORY;
    private final static CustomerRepository CUSTOMER_REPOSITORY;
    private final static ExpertRepository EXPERT_REPOSITORY;
    private final static MainServiceRepository MAIN_SERVICE_REPOSITORY;
    private final static OfferRepository OFFER_REPOSITORY;
    private final static OrderRepository ORDER_REPOSITORY;
    private final static SubServiceRepository SUB_SERVICE_REPOSITORY;

    private final static AdminService ADMIN_SERVICE;
    private final static CommentService COMMENT_SERVICE;
    private final static CustomerService CUSTOMER_SERVICE;
    private final static ExpertService EXPERT_SERVICE;
    private final static MainServiceService MAIN_SERVICE_SERVICE;
    private final static OfferService OFFER_SERVICE;
    private final static OrderService ORDER_SERVICE;
    private final static SubServiceService SUB_SERVICE_SERVICE;

    private AppContext() {}

    static {
        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager entityManager = emf.createEntityManager();

        ADMIN_REPOSITORY = new AdminRepositoryImpl(entityManager);
        ADMIN_SERVICE = new AdminServiceImpl(ADMIN_REPOSITORY);

        COMMENT_REPOSITORY = new CommentRepositoryImpl(entityManager);
        COMMENT_SERVICE = new CommentServiceImpl(COMMENT_REPOSITORY);

        CUSTOMER_REPOSITORY = new CustomerRepositoryImpl(entityManager);
        CUSTOMER_SERVICE = new CustomerServiceImpl(CUSTOMER_REPOSITORY);

        EXPERT_REPOSITORY = new ExpertRepositoryImpl(entityManager);
        EXPERT_SERVICE = new ExpertServiceImpl(EXPERT_REPOSITORY);

        MAIN_SERVICE_REPOSITORY = new MainServiceRepositoryImpl(entityManager);
        MAIN_SERVICE_SERVICE = new MainServiceServiceImpl(MAIN_SERVICE_REPOSITORY);

        OFFER_REPOSITORY = new OfferRepositoryImpl(entityManager);
        OFFER_SERVICE = new OfferServiceImpl(OFFER_REPOSITORY);

        ORDER_REPOSITORY = new OrderRepositoryImpl(entityManager);
        ORDER_SERVICE = new OrderServiceImpl(ORDER_REPOSITORY);

        SUB_SERVICE_REPOSITORY = new SubServiceRepositoryImpl(entityManager);
        SUB_SERVICE_SERVICE = new SubServiceServiceImpl(SUB_SERVICE_REPOSITORY);
    }

    public static AdminService getAdminService() {
        return ADMIN_SERVICE;
    }

    public static CommentService getCommentService() {
        return COMMENT_SERVICE;
    }

    public static CustomerService getCustomerService() {
        return CUSTOMER_SERVICE;
    }

    public static ExpertService getExpertService() {
        return EXPERT_SERVICE;
    }

    public static MainServiceService getMainServiceService() {
        return MAIN_SERVICE_SERVICE;
    }

    public static OfferService getOfferService() {
        return OFFER_SERVICE;
    }

    public static OrderService getOrderService() {
        return ORDER_SERVICE;
    }

    public static SubServiceService getSubServiceService() {
        return SUB_SERVICE_SERVICE;
    }
}
