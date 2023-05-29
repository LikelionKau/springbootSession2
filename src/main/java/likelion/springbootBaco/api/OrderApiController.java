package likelion.springbootBaco.api;

import likelion.springbootBaco.domain.Order;
import likelion.springbootBaco.dto.OrderDto;
import likelion.springbootBaco.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;

//    /**
//     * V1. 엔티티 직접 노출
//     * - Hibernate5Module 모듈 등록, LAZY=null 처리
//     * - 양방향 관계 문제 발생 -> @JsonIgnore
//     */
//    @GetMapping("/api/v1/orders")
//    public List<Order> ordersV1() {
//        List<Order> all = orderRepository.findAllByString(new OrderSearch());
//        for (Order order : all) {
//            order.getMember().getName(); //Lazy 강제 초기화
//            order.getDelivery().getAddress(); //Lazy 강제 초기환
//            List<OrderItem> orderItems = order.getOrderItems();
//            orderItems.stream().forEach(o -> o.getItem().getName()); //Lazy 강제
//            초기화
//        }
//        return all;
//    }

    /**
     * V2. DTO 사용
     * 지연 로딩으로 너무 많은 SQL 실행
     * SQL 실행 수:
     *  - order 1번
     *  - member , address N번(order 조회 수 만큼)
     *  - orderItem N번(order 조회 수 만큼)
     *  - item N번(orderItem 조회 수 만큼)
     */
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(toList());
        return result;
    }

    /**
     * V3. DTO + JOIN FETCH 사용
     * 페치 조인으로 SQL이 1번만 실행됨
     * distinct 를 사용한 이유는 1대다 조인이 있으므로 데이터베이스 row가 증가한다.
     * 그 결과 같은 order 엔티티의 조회 수도 증가하게 된다.
     * JPA의 distinct는 SQL에 distinct를 추가하고, 더해서 같은 엔티티가 조회되면 애플리케이션에서 중복을 걸러준다.
     * 이 예에서 order가 컬렉션 페치 조인 때문에 중복 조회 되는 것을 막아준다.
     * - 단점 : 페이징 불가능
     */
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(toList());
        return result;
    }
}
