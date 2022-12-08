package ir.maktab.entity.offer;

import ir.maktab.base.entity.BaseEntity;
import ir.maktab.entity.order.Order;
import ir.maktab.entity.person.Expert;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Offer extends BaseEntity<Long> {

    private String offer;
    @Column(name = "proposed-price")
    private Long proposedPrice;
    @Column(name = "time_type")
    private TimeType timeType;
    @Column(name = "duration-time")
    private Integer durationTime;
    @Column(name = "is-accept")
    private Boolean isAccept;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Expert expert;

    public Offer(String offer, Long proposedPrice, TimeType timeType, Integer durationTime) {
        this.offer = offer;
        this.proposedPrice = proposedPrice;
        this.timeType = timeType;
        this.durationTime = durationTime;
    }
}
