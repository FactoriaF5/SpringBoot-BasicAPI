package com.factoria.coders.controllers;

import com.factoria.coders.models.Coder;
import com.factoria.coders.repositories.CoderRepository;
import com.factoria.coders.repositories.ICoderRepository;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CoderController {

    private ICoderRepository coderRepository;

    public CoderController(ICoderRepository coderRepository) {
        this.coderRepository = coderRepository;
        var coder = new Coder();
        coder.setName("hola");
        coderRepository.save(coder);
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

        var coder = coderRepository.findById(id).get();
        return coder;
    }

    @GetMapping("/coders/search")
    List<Coder> search(@RequestParam("name") String search){
        System.out.println(search);
        var list = getCoderList();
        var filteredList = list.stream().filter(x->x.getName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        return filteredList;
    }

    @PostMapping("/coders")
    Coder createCoder(@RequestBody Coder coder) {
        return coderRepository.save(coder);
    }
}
