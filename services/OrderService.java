package kg.megacom.miniTinder.services;

import kg.megacom.miniTinder.models.Order;
import kg.megacom.miniTinder.models.User;

import java.util.List;

public interface OrderService {
    void save(Order order);
    void update(Order order);
    List<Order> findAll();
    Order findById(Long id);
    List<Order> findMatch(User user);
    List<Order> findWait(User user);
    List<Order> findYourOrders(User user);

}
