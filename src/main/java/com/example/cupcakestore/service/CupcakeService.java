package com.example.cupcakestore.service;
import org.springframework.stereotype.Service;
import com.example.cupcakestore.repository.CupcakeRepository;
import com.example.cupcakestore.model.Cupcake;
import java.util.List;
import java.util.Optional;

@Service
public class CupcakeService {
    private final CupcakeRepository repo;
    public CupcakeService(CupcakeRepository repo){ this.repo = repo; }

    public List<Cupcake> listAll(){ return repo.findAll(); }
    public Optional<Cupcake> find(Long id){ return repo.findById(id); }
    public Cupcake save(Cupcake c){ return repo.save(c); }
    public void delete(Long id){ repo.deleteById(id); }
}
