package likelion.springbootBaco.dto;

import likelion.springbootBaco.domain.Address;
import likelion.springbootBaco.domain.Order;
import likelion.springbootBaco.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class OrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getOrderStatus();
        address = order.getDelivery().getAddress();
        orderItems = order.getOrderItemList().stream()
                .map(orderItem -> new OrderItemDto(orderItem))
                .collect(toList());
    }
}


