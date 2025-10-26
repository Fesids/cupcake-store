package com.example.cupcakestore.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable=false)
    public String name;
    @Column(nullable=false, unique=true)
    public String email;
    @Column(nullable=false)
    public String passwordHash;
    public LocalDateTime createdAt = LocalDateTime.now();

    public User(){}
    public User(String name, String email, String passwordHash){
        this.name = name; this.email = email; this.passwordHash = passwordHash;
    }
}
