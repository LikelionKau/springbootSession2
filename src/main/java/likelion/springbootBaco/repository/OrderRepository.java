package likelion.springbootBaco.repository;

import likelion.springbootBaco.domain.Order;
import likelion.springbootBaco.dto.OrderSimpleQueryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o from Order o JOIN FETCH o.member m JOIN FETCH o.delivery d")
    public List<Order> findAllWithMemberDelivery();

    @Query("SELECT NEW likelion.springbootBaco.dto.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.orderStatus, d.address) FROM Order o JOIN o.member m JOIN o.delivery d")
    public List<OrderSimpleQueryDto> findOrderDtoList();

    @Query("SELECT DISTINCT o from Order o JOIN FETCH o.member m JOIN FETCH o.delivery d JOIN FETCH o.orderItemList oi JOIN FETCH oi.item i")
    public List<Order> findAllWithItem();
}
