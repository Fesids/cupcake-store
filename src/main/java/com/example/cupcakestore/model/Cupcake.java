package com.example.cupcakestore.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Cupcake {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable=false)
    public String name;
    @Lob
    public String description;
    @Column(nullable=false)
    public BigDecimal price;
    public String imageUrl;
    public LocalDateTime createdAt = LocalDateTime.now();

    public Cupcake(){}
    public Cupcake(String name, String desc, BigDecimal price){
        this.name = name; this.description = desc; this.price = price;
    }
}
