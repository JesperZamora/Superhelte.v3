package com.example.superheltev3.repositories;

import com.example.superheltev3.Exception.ResourceNotFoundException;
import com.example.superheltev3.models.Superhelt;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SuperheltRepositories {
    private List<Superhelt> superheltList;

    public SuperheltRepositories() {
        superheltList = new ArrayList<>(List.of(
                new Superhelt("Superman", "Clark", "Flying", 32),
                new Superhelt("Batman", "Bruce", "Rich $", 35),
                new Superhelt("Flash", "Barry", "Running", 28)
        ));
    }


    public List<Superhelt> getSuperheltList() {
        return superheltList;
    }

    public Superhelt getSuperhelt(String navn) throws ResourceNotFoundException {
        for(Superhelt superhelt : superheltList) {
            if (!superhelt.getHeroName().toLowerCase().contains(navn.toLowerCase())) {
                throw new ResourceNotFoundException("Name does not exist");
            } else {
                return superhelt;
            }
        }
       return null;
    }

    public Superhelt addSuperhelt(Superhelt superhelt){
        superheltList.add(superhelt);
        return superhelt;
    }


    public Superhelt editSuperhelt(Superhelt superhelt) throws ResourceNotFoundException{
        for(int i = 0; i < superheltList.size(); i++){
            if(superhelt.getHeroName().contains(superheltList.get(i).getHeroName())){
                superheltList.set(i, superhelt);
                return superhelt;
            } else {
                throw new ResourceNotFoundException("No hero to edit by that name");
            }
        }
        return null;
    }


    public String sletSuperhelt(String navn) throws ResourceNotFoundException {
        Superhelt superhelt = getSuperhelt(navn);
        superheltList.remove(superhelt);
        return "Superhelt slettet";
    }
}
