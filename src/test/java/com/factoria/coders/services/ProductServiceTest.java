package com.factoria.coders.services;

import com.factoria.coders.dtos.ProductRequestDto;
import com.factoria.coders.models.Product;
import com.factoria.coders.models.User;
import com.factoria.coders.repositories.IProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ProductServiceTest {

    @MockBean
    IProductRepository productRepository;




    @Test
    void getAll() {
    }

    @Test
    void getByIdReturnsAProductResponseDTO() {
        Product product = createTestProduct();
        Mockito.when(this.productRepository.findById(1L)).thenReturn(Optional.of(product));

        var service = createProductService();
        var sut = service.getById(1L);

        assertThat(1L, equalTo(sut.getId()));
    }

    private ProductService createProductService() {

        return new ProductService(productRepository);
    }

    private Product createTestProduct() {
        var product = new Product();
        product.setName("Product1");
        product.setId(1L);
        product.setDescription("descript");
        return product;
    }

    @Test
    void search() {
    }

    @Test
    void createProductWithAtributesAnsUser() {

        var authUser = new User();
        authUser.setId(1L);

        var product = createTestProduct();
        product.setAuthor(authUser);
        var productService = new ProductService(productRepository);
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);

        var productRequest = new ProductRequestDto("Product1","description");

        var sut = productService.createProduct(productRequest, authUser);
//        sut.getName();
        assertThat(sut.getAuthor(), equalTo(authUser));
        assertThat(sut.getName(), equalTo("Product1"));
    }

    @Test
    void uptdateProduct() {
    }
}