package com.factoria.coders.mappers;

import com.factoria.coders.dtos.ProductRequestDto;
import com.factoria.coders.models.Product;
import com.factoria.coders.models.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void mapToProductDTO() {

        var mapper = new ProductMapper();
        var author = new User();
        var product = Product.builder().author(author)
                .id(1L)
                .name("productName")
                .build();

        var sut = mapper.mapToProductDTO(product);

        assertEquals(product.getName(), sut.getName());

    }

    @Test
    void mapToProduct() {

        var mapper = new ProductMapper();
        var author = new User();
        var productDto = ProductRequestDto.builder()
                .description("des")
                .name("name").build();

        var sut = mapper.mapToProduct(productDto);

        assertEquals(productDto.getName(), sut.getName());

    }
}