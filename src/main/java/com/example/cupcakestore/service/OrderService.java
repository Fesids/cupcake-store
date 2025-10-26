package com.example.cupcakestore.service;
import org.springframework.stereotype.Service;
import com.example.cupcakestore.repository.OrderRepository;
import com.example.cupcakestore.model.Order;
import com.example.cupcakestore.model.OrderItem;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repo;
    public OrderService(OrderRepository repo){ this.repo = repo; }

    public Order createOrder(Order order){
        BigDecimal total = BigDecimal.ZERO;
        for(OrderItem it : order.items){
            BigDecimal qty = BigDecimal.valueOf(it.quantity);
            total = total.add(it.price.multiply(qty));
        }
        order.total = total;
        return repo.save(order);
    }
    public List<Order> findByUserId(Long userId){ return repo.findByUserId(userId); }
}
