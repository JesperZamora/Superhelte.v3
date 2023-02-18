package com.example.superheltev3.repositories;

import com.example.superheltev3.models.Superhelt;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SuperheltRepositories {
    //database fra superhelt-projektet skal ind her
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

    public Superhelt getSuperhelt(String navn) throws Exception{
        for(Superhelt superhelt : superheltList) {
            if (!superhelt.getHeroName().toLowerCase().contains(navn.toLowerCase())) {
                throw new Exception("No match");
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


    public Superhelt editSuperhelt(Superhelt superhelt){
        for(int i = 0; i < superheltList.size(); i++){
            if(superhelt.getHeroName().contains(superheltList.get(i).getHeroName())){
                superheltList.set(i, superhelt);
                return superhelt;
            }
        }
        return null;
    }


    public String sletSuperhelt(String navn) throws Exception {
        Superhelt superhelt = getSuperhelt(navn);
        superheltList.remove(superhelt);
        return "Superhelt slettet";
    }
}
