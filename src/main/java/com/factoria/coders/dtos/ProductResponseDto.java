package com.factoria.coders.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class ProductResponseDto {
    private Long id;
    private  String name;
    private String description;
    private Object user;


    private class User {
        private Long id;
        private String userName;
        private String img;
    }

}
