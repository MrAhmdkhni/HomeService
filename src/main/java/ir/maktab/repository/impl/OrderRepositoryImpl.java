package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entity.order.Order;
import ir.maktab.repository.OrderRepository;
import jakarta.persistence.EntityManager;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Order, Long> implements OrderRepository {

    public OrderRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }
}
