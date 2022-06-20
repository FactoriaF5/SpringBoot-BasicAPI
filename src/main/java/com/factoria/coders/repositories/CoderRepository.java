package com.factoria.coders.repositories;

import com.factoria.coders.models.Coder;
import org.springframework.stereotype.Repository;

import java.util.List;


public class CoderRepository {
    private List<Coder> getCoderList() {
        return List.of
                (new Coder("Alex",1L),
                        new Coder("marta", 2L),
                        new Coder("Alex", 3L));
    }

    public List<Coder> findAll() {
        return this.getCoderList();
    }

    public Coder findById(Long id) {
        var list = getCoderList();

        var a = list.stream().filter(x -> x.getId() == id).findFirst().get();
        return a;
    }
}
