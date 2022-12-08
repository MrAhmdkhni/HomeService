package ir.maktab.entity.service;

import ir.maktab.base.entity.BaseEntity;
import ir.maktab.entity.order.Order;
import ir.maktab.entity.person.Expert;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class SubService extends BaseEntity<Long> {

    @Column(nullable = false)
    private String name;
    @Column(name = "base_price")
    private Long basePrice;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MainService mainService;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Expert> experts = new HashSet<>();
    @OneToMany(mappedBy = "subService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public SubService(String name, Long basePrice, String description) {
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
    }

    public SubService(String name, Long basePrice, String description, MainService mainService) {
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.mainService = mainService;
    }

    @Override
    public String toString() {
        return "SubService{" +
                "name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", description='" + description + '\'' +
                ", main-service=" + mainService.getName() +
                '}';
    }
}
