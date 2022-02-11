package t.spring.tacos.repo;

import t.spring.tacos.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
