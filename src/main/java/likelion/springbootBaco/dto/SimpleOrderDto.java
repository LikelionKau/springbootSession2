package likelion.springbootBaco.dto;

import likelion.springbootBaco.domain.Address;
import likelion.springbootBaco.domain.Order;
import likelion.springbootBaco.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; // 주문 시간
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getOrderStatus();
        address = order.getDelivery().getAddress();
    }
}
