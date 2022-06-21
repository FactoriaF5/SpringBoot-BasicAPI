package com.factoria.coders.controllers;

import com.factoria.coders.models.Product;
import com.factoria.coders.repositories.IProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private IProductRepository productRepository;

    public ProductController(IProductRepository productRepository) {
        this.productRepository = productRepository;
        var coder = new Product();
        coder.setName("hola");
        productRepository.save(coder);
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello Coders";
    }

    @GetMapping("/coders")
    List<Product> getAll() {

        var coderList = this.productRepository.findAll();
        return coderList;
    }

//    private List<Product> getCoderList() {
//        return List.of
//                (new Product("Alex",1L),
//                        new Product("marta", 2L),
//                        new Product("Alex", 3L));
//    }

    @GetMapping("/coders/{id}")
    Product getById(@PathVariable Long id){

        var coder = productRepository.findById(id).get();
        return coder;
    }

//    @GetMapping("/coders/search")
//    List<Product> search(@RequestParam("name") String search){
//        System.out.println(search);
//        var list = getCoderList();
//        var filteredList = list.stream().filter(x->x.getName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
//        return filteredList;
//    }

    @PostMapping("/coders")
    Product createCoder(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/coders/{id}")
    Product updateProduct (@PathVariable("id") Long id, @RequestBody Product product) {
        var dBProduct = productRepository.findById(id);
        if (dBProduct.isPresent()) {
            product.setId(id);
            productRepository.save(product);
            return product;
        }
        return product;
    }
}
