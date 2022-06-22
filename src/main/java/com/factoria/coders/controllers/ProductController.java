package com.factoria.coders.controllers;

import com.factoria.coders.models.Product;
import com.factoria.coders.repositories.IProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private IProductRepository productRepository;

    public ProductController(IProductRepository productRepository) {
        this.productRepository = productRepository;
        var coder = new Product();
        coder.setName("hola");
        coder.setDescription("esta es la descripción");
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

    @GetMapping("/coders/search")
    ResponseEntity<List<Product>> search(@RequestParam("name") String search){

        var filteredList = productRepository.findByNameContainsOrDescriptionContainsAllIgnoreCase(search,search);
        if (filteredList.size() == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }

    @PostMapping("/coders")
    Product createCoder(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/coders/{id}")
    ResponseEntity<Product> updateProduct (@PathVariable("id") Long id, @RequestBody Product product) {
        var dBProduct = productRepository.findById(id);
        if (dBProduct.isPresent()) {
            product.setId(id);
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
