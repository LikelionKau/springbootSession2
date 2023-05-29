package likelion.springbootBaco.api;

import likelion.springbootBaco.domain.Order;
import likelion.springbootBaco.dto.OrderSimpleQueryDto;
import likelion.springbootBaco.dto.SimpleOrderDto;
import likelion.springbootBaco.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

//    @GetMapping("/api/v1/simple-orders")
//    public List<Order> ordersV1() {
//        List<Order> all = orderRepository.findAllByString(new OrderSearch());
//        for (Order order : all) {
//            order.getMember().getName(); // LAZY 강제 초기화
//            order.getDelivery().getAddress(); // LAZY 강제 초기화
//            /**
//             * 위와 같이 코딩함으로써 프록시 대신 DB로부터 실제 객체를 불러오게끔 함.
//             */
//        }
//
//        return all;
//    }

    /**
     * V2. 엔티티를 조회해서 dto로 변환 (fetch join 사용x)
     * 단점 : 지연로딩으로 인해 쿼리가 N번 호출되어야 함
     */
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderListV2() {
        List<Order> orderList = orderRepository.findAll();
        List<SimpleOrderDto> result = orderList.stream()
                .map(SimpleOrderDto::new)
//                .map(o -> new SimpleOrderDto(o))
                .collect(toList());
        return result;
    }

    /**
     * V3. 엔티티를 조회하여 dto로 변환(fetch join 사용o)
     * fetch join으로 쿼리를 1번 호출
     * 참고 : fetch join에 대한 자세한 내용은 JPA 기본편에 있음
     */
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orderList = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> _result = new ArrayList<>();
        for (Order order : orderList) {
            SimpleOrderDto simpleOrderDto = new SimpleOrderDto(order);
            _result.add(simpleOrderDto);
        }
//        List<SimpleOrderDto> result = orderList.stream()
//                .map(o -> new SimpleOrderDto(o))
//                .collect(toList());
        return _result;
    }

    /**
     * V4. JPA에서 DTO로 바로 조회
     * - 쿼리 1번 호출
     * - select 절에서 원하는 데이터만 선택해서 조회
     */
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderRepository.findOrderDtoList();
    }


}
