package com.factoria.coders.controllers;

import com.factoria.coders.models.Coder;
import com.factoria.coders.repositories.CoderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CoderController {

    private CoderRepository coderRepository;

    public CoderController(CoderRepository coderRepository) {
        this.coderRepository = coderRepository;
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello Coders";
    }

    @GetMapping("/coders")
    List<Coder> getAll() {

        var coderList = this.coderRepository.findAll();
        return coderList;
    }

    private List<Coder> getCoderList() {
        return List.of
                (new Coder("Alex",1L),
                        new Coder("marta", 2L),
                        new Coder("Alex", 3L));
    }

    @GetMapping("/coders/{id}")
    Coder getById(@PathVariable Long id){

        var coder = coderRepository.findById(id);
        return coder;
    }

    @GetMapping("/coders/search")
    List<Coder> search(@RequestParam("name") String search){
        System.out.println(search);
        var list = getCoderList();
        var filteredList = list.stream().filter(x->x.getName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        return filteredList;
    }
}
