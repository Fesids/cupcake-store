package com.example.cupcakestore.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders_table")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    public com.example.cupcakestore.model.User user;
    public BigDecimal total = BigDecimal.ZERO;
    public String status = "NOVO";
    public LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL)
    public List<OrderItem> items = new ArrayList<>();

    public Order(){}
}
