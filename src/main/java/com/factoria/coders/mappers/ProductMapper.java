package com.factoria.coders.mappers;

import com.factoria.coders.dtos.ProductRequestDto;
import com.factoria.coders.dtos.ProductResponseDto;
import com.factoria.coders.models.Product;

public class ProductMapper {

    public ProductResponseDto mapToProductDTO(Product product) {
        var dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        return dto;
    };

    public Product mapToProduct(ProductRequestDto productRequestDto) {
        var product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        return product;
    }


}
