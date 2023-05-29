package likelion.springbootBaco;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import likelion.springbootBaco.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    /**
     * 생성자 호출하여 Bean 생성 -> @Autowired 의존성 주입 -> PostConstruct 실행
     */
    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit() {
            Address address1 = new Address("서울시", "용산구", "길", "11111");
            Address address2 = new Address("갓갓갓", "갓갓갓", "갓", "11111");
            Member member1 = Member.createMember("황제철", address1);
            Member member2 = Member.createMember("김주안", address2);
            em.persist(member1);
            em.persist(member2);


            Item item1 = new Item();
            item1.setBrand("무신사");
            item1.setName("스탠다드");
            item1.setPrice(100);
            item1.setStock(100);
            Item item2 = new Item();
            item2.setBrand("나이키");
            item2.setName("조던");
            item2.setPrice(300);
            item2.setStock(300);
            OrderItem orderItem1 = OrderItem.createOrderItem(item1, item1.getPrice(), 10);
            List<OrderItem> orderItemList1 = new ArrayList<>();
            orderItemList1.add(orderItem1);
            OrderItem orderItem2 = OrderItem.createOrderItem(item2, item2.getPrice(), 50);
            List<OrderItem> orderItemList2 = new ArrayList<>();
            orderItemList2.add(orderItem2);
            item1.setOrderItem(orderItemList1);
            item2.setOrderItem(orderItemList2);
            em.persist(item1);
            em.persist(item2);

            Order order1 = Order.createOrder(member1, item1.getOrderItem().get(0));
            Order order2 = Order.createOrder(member2, item2.getOrderItem().get(0));
            em.persist(order1);
            em.persist(order2);
        }
    }

}
