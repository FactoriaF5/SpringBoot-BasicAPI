package com.factoria.coders.models;

public class Coder {
    private  String name = "coder1";
    private Long id;

    public Long getId() {
        return id;
    }

    public Coder(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
