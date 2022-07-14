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

    @Test
    void canCountProductLikes() {
        var product = new Product();
        var user = new User();
        var like = new Like(user,product);
        product.addLike(like);

        var sut = product.likesCount();

        assertThat(sut, equalTo(1));

    }
    @Test
    void canNotAddAnOtherProductLike() {
        var product = new Product();
        var product2 = new Product();
        var user = new User();
        var like = new Like(user,product2);
        product.addLike(like);

        var sut = product.likesCount();

        assertThat(sut, equalTo(0));
    }

    @Test
    void canKnowIfUserIsProductInLove() {
        var product = new Product();
        var user = new User();
        var like = new Like(user,product);
        product.addLike(like);

        boolean sut = product.isLovedBy(user);

        assertThat(sut, equalTo(true));
    }

    @Test
    void canKnowIfUserIsNotProductInLove() {
        var product = new Product();
        var user = new User();
        var user2 = new User();
        var like = new Like(user,product);
        product.addLike(like);

        boolean sut = product.isLovedBy(user2);

        assertThat(sut, equalTo(false));
    }

}