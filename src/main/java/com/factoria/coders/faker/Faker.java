package com.factoria.coders.faker;

import com.factoria.coders.models.Product;
import com.factoria.coders.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public class Faker {


    private IProductRepository iProductRepository;

    public Faker(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    public void createData() {
//        var productList = List.of(new Product(1L,"product1","decription product1"));

//        iProductRepository.saveAll(productList);
    }

}
