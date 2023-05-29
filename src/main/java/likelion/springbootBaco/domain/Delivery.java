package likelion.springbootBaco.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static likelion.springbootBaco.domain.Delivery.DeliveryStatus.ESTABLISHED;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Delivery {
    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Enumerated(STRING)
    private DeliveryStatus deliveryStatus;

//    private String city;
//    private String state;
//    private String street;
//    private String zipcode;

    private Address address;

    public static Delivery createDelivery(Order order, Address address) {
        Delivery delivery = new Delivery();
        delivery.order = order;
        delivery.deliveryStatus = ESTABLISHED;
        delivery.address = address;
        return delivery;
    }

    public enum DeliveryStatus {
        ESTABLISHED, PROGRESS, DONE
    }
}
