package com.factoria.coders.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

class ProductTest {

    @Test
    void canCreateProductWithAttributes() {
        var product = new Product();
        product.setName("name");
        product.setDescription("description");
        product.setId(1L);
        assertThat(product.getName(),equalTo("name"));
        assertThat(product.getDescription(),equalTo("description"));
    }

    @Test
    void countComments() {
        var product = new Product();
        var comment = new Comment();
        product.setComments(List.of(comment));
        assertThat(product.countComments(),equalTo(1));
    }

    @Test
    void productMustHaveAnAuthor() {

    }
}