package com.example.superheltev3.services;

import com.example.superheltev3.models.Superhelt;
import org.springframework.stereotype.Service;
import com.example.superheltev3.repositories.SuperheltRepositories;

import java.util.List;

@Service
public class SuperheltService {
    private SuperheltRepositories superheltRepo;

    // Automatic dependency injection
    public SuperheltService(SuperheltRepositories superheltRepo){
        this.superheltRepo = superheltRepo;
    }

    // All heroes in list
    public List<Superhelt> getSuperheltList() {
        return superheltRepo.getSuperheltList();
    }

    // One hero by index number
    public Superhelt getSuperhelt(String navn){
        return superheltRepo.getSuperhelt(navn);
    }

    public Superhelt addSuperhelt(Superhelt superhelt){
        return superheltRepo.addSuperhelt(superhelt);
    }

    public Superhelt editSuperhelt(Superhelt superhelt){
        return superheltRepo.editSuperhelt(superhelt);
    }

    public String sletSuperhelt(String navn){
        return superheltRepo.sletSuperhelt(navn);
    }
}
