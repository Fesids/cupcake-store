package com.example.cupcakestore.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cupcakestore.model.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
