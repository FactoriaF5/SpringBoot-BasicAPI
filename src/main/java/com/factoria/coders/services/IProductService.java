package com.factoria.coders.services;

import com.factoria.coders.dtos.ProductRequestDto;
import com.factoria.coders.dtos.ProductResponseDto;
import com.factoria.coders.models.Product;
import com.factoria.coders.models.User;

import java.util.List;

public interface IProductService {

    List<Product> getAll();
    ProductResponseDto getById(Long id);
    List<Product> search();
    Product createProduct(ProductRequestDto productRequestDto, User user);
    Product uptdateProduct();
}
