package com.example.cupcakestore.service;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;
import com.example.cupcakestore.repository.UserRepository;
import com.example.cupcakestore.model.User;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo){ this.repo = repo; }

    public User register(String name, String email, String password){
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        User u = new User(name, email, hash);
        return repo.save(u);
    }
    public Optional<User> authenticate(String email, String password){
        Optional<User> ou = repo.findByEmail(email);
        if(ou.isEmpty()) return Optional.empty();
        User u = ou.get();
        if(BCrypt.checkpw(password, u.passwordHash)) return Optional.of(u);
        return Optional.empty();
    }
    public Optional<User> findById(Long id){ return repo.findById(id); }
}
