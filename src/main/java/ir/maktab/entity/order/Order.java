package ir.maktab.entity.order;

import ir.maktab.base.entity.BaseEntity;
import ir.maktab.entity.offer.Offer;
import ir.maktab.entity.person.Customer;
import ir.maktab.entity.service.SubService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
public class Order extends BaseEntity<Long> {

    @Column(name = "proposed-price")
    private Long proposedPrice;
    private String description;
    @Column(name = "execution_time")
    private LocalDateTime executionTime;
    private String address;
    @Column(name = "order_status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SubService subService;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers  = new ArrayList<>();

    public Order(Long proposedPrice, String description, LocalDateTime executionTime, String address, OrderStatus orderStatus, Customer customer, SubService subService) {
        this.proposedPrice = proposedPrice;
        this.description = description;
        this.executionTime = executionTime;
        this.address = address;
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.subService = subService;
    }
}
