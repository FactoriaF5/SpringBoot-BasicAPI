package com.factoria.coders.controllers;

import com.factoria.coders.dtos.ProductRequestDto;
import com.factoria.coders.dtos.ProductResponseDto;
import com.factoria.coders.faker.Faker;
import com.factoria.coders.models.Comment;
import com.factoria.coders.models.Product;
import com.factoria.coders.repositories.ICommentRepository;
import com.factoria.coders.repositories.IProductRepository;
import com.factoria.coders.services.IProductService;
import com.factoria.coders.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private IProductRepository productRepository;
    private IProductService productService;

    private IUserService userService;

    public ProductController(IProductRepository productRepository, IProductService productService, IUserService userService) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello Coders";
    }

    @GetMapping("/products")
    List<Product> getAll() {

       return this.productService.getAll();
    }

//    private List<Product> getCoderList() {
//        return List.of
//                (new Product("Alex",1L),
//                        new Product("marta", 2L),
//                        new Product("Alex", 3L));
//    }

    @GetMapping("/products/{id}")
    ResponseEntity<ProductResponseDto> getById(@PathVariable Long id){

        var product = productService.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products/search")
    ResponseEntity<List<Product>> search(@RequestParam("name") String search){

        var filteredList = productRepository.findByNameContainsOrDescriptionContainsAllIgnoreCase(search,search);
        if (filteredList.size() == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }

    @PostMapping("/products")
    Product createCoder(@RequestBody ProductRequestDto productdto) {
        var user=userService.findById(1L);
        return productService.createProduct(productdto, user);
    }

    @PutMapping("/products/{id}")
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
