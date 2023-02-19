package com.example.superheltev3.controllers;

import com.example.superheltev3.Exception.ResourceNotFoundException;
import com.example.superheltev3.models.Superhelt;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.example.superheltev3.services.SuperheltService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/superhelte")
@Controller
public class SuperheltController {
    private SuperheltService superheltService;

    public SuperheltController(SuperheltService superheltService) {
        this.superheltService = superheltService;
    }

    // all heroes from repo
    @RequestMapping("/")
    public ResponseEntity<List<Superhelt>> alleSuperhelte() {
        List<Superhelt> superheltListe = superheltService.getSuperheltList();
        return new ResponseEntity<>(superheltListe, HttpStatus.OK);
    }

    // one superhero by index number
    @RequestMapping("/{navn}")
    public ResponseEntity<Superhelt> enkeltSuperhelt(@PathVariable String navn) {
        try {
            Superhelt superhelt = superheltService.getSuperhelt(navn.toLowerCase());
            return new ResponseEntity<>(superhelt, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    // all heroes in HTML + RequestParam
    @RequestMapping("")
    public ResponseEntity<String> htmlSuperhelt(@RequestParam String format) {
        List<Superhelt> superheltListe = superheltService.getSuperheltList();

        StringBuilder sb = new StringBuilder(); // concat html strings using SB class

        HttpHeaders header = new HttpHeaders();
        header.set("Content-type", "text/html");

        if (format != null && format.equals("html")) {
            for (Superhelt superhelt : superheltListe) {

                sb.append("<html><head> <style> span {color: #E75480; font-weight: bold; font-size: 18px;} </style> </head> <body> <p>");

                sb.append("<span class=bold>").append("Hero name: ").append("</span>").append(superhelt.getHeroName()).append("<br>");
                sb.append("<span class=bold>").append("Civil name: ").append("</span>").append(superhelt.getCivilName()).append("<br>");
                sb.append("<span class=bold>").append("Age: ").append("</span>").append(superhelt.getAge()).append("<br>");
                sb.append("<span class=bold>").append("Power: ").append("</span>").append(superhelt.getHeroPower()).append("<br>");

                sb.append("</p> </<body></html>");
            }
            return new ResponseEntity<>(sb.toString(), header, HttpStatus.OK);
        }

        return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/opret")
    public ResponseEntity<Superhelt> tilføjSuperhelt(@RequestBody Superhelt superhelt) {
        Superhelt superhelt1 = superheltService.addSuperhelt(superhelt);
        return new ResponseEntity<>(superhelt1, HttpStatus.OK);
    }

    @PutMapping("/ret")
    public ResponseEntity<Superhelt> retSuperhelt(@RequestBody Superhelt superhelt) {
        try {
            Superhelt superhelt2 = superheltService.editSuperhelt(superhelt);
            return new ResponseEntity<>(superhelt2, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/slet/{navn}")
    public ResponseEntity<String> sletSuperhelt(@PathVariable String navn) {
        String response;
        try {
            response = superheltService.sletSuperhelt(navn.toLowerCase());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}