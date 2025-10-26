package com.example.cupcakestore.model;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    public Cupcake cupcake;
    public int quantity;
    public BigDecimal price; // price per unit when ordered

    public OrderItem(){}
    public OrderItem(Cupcake cupcake, int q){
        this.cupcake = cupcake; this.quantity = q;
        this.price = cupcake.price;
    }
}
