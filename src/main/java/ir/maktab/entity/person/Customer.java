package ir.maktab.entity.person;

import ir.maktab.entity.order.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Customer extends Person{

    private Long credit;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public Customer(String firstname, String lastname, String phoneNumber, String email, String password, Long credit) {
        super(firstname, lastname, phoneNumber, email, password);
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Customer {" +
                "firstname='" + getFirstname() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", email='" + getEmail() + '\'' +
                "credit=" + credit +
                "} ";
    }
}
