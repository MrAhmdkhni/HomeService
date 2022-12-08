package ir.maktab.entity.person;

import ir.maktab.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="person_type")
@NoArgsConstructor
@Getter
@Setter
public class Person extends BaseEntity<Long> {

    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(name = "phone_number", length = 11, unique = true, nullable = false)
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false, length = 8)
    private String password;

    public Person(String firstname, String lastname, String phoneNumber, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
