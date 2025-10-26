package com.example.cupcakestore.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cupcakestore.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
