package ir.maktab.entity.service;

import ir.maktab.base.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "main_service")
@Setter
@Getter
@NoArgsConstructor
public class MainService extends BaseEntity<Long> {

    private String name;
    @OneToMany(mappedBy = "mainService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubService> subServices = new ArrayList<>();

    public MainService(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Main Service {" +
                "name='" + name + '\'' +
                "} ";
    }

}
