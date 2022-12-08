package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseServiceImpl;
import ir.maktab.entity.order.Order;
import ir.maktab.repository.OrderRepository;
import ir.maktab.service.OrderService;

public class OrderServiceImpl
        extends BaseServiceImpl<Order, Long, OrderRepository>
        implements OrderService {

    public OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }
}
