package com.example.cupcakestore.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cupcakestore.model.Cupcake;

public interface CupcakeRepository extends JpaRepository<Cupcake, Long> { }
