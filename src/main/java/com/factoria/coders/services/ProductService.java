package com.factoria.coders.services;

import com.factoria.coders.dtos.ProductRequestDto;
import com.factoria.coders.dtos.ProductResponseDto;
import com.factoria.coders.exceptions.NotFoundException;
import com.factoria.coders.mappers.ProductMapper;
import com.factoria.coders.models.Product;
import com.factoria.coders.models.User;
import com.factoria.coders.repositories.IProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ProductService implements IProductService{
    IProductRepository productRepository;
    IUserService userService;

    public ProductService(IProductRepository productRepository, IUserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public List<Product> getAll() {

        var products = this.productRepository.findAll();
        var authUser = userService.findById(1L);
        products.stream().forEach(x-> x.isLovedBy(authUser));

        return products;
    }

    @Override
    public ProductResponseDto getById(Long id) {
        var product = productRepository.findById(id);
        if (product.isPresent()) {
            var productResponse = new ProductMapper().mapToProductDTO(product.get());
            return productResponse;
        }
        throw new NotFoundException("product id:"+ id +" found", "p-321");
    }
//    @Override
//    public ProductResponseDto getById(Long id) {
//        var product = productRepository.findById(id);
//        if (product.isPresent()) {
//            var productResponse = new ProductMapper().mapToProductDTO(product.get());
//            return productResponse;
//        }
//        throw new RuntimeException("Product " + id + " Not Found");
//    }

    @Override
    public List<Product> search() {
        return null;
    }

    @Override
    public Product createProduct(ProductRequestDto productDto, User user) {
//        var product = new Product();
//        product.setName(productDto.getName());
//        product.setDescription(productDto.getDescription());
        var product = new ProductMapper().mapToProduct(productDto);
        product.setAuthor(user);
        productRepository.save(product);

        return productRepository.save(product);
    }

    @Override
    public Product uptdateProduct() {
        return null;
    }
}
