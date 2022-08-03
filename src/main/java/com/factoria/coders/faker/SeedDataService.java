package com.factoria.coders.faker;

import com.factoria.coders.models.Product;
import com.factoria.coders.models.User;
import com.factoria.coders.repositories.IProductRepository;
import com.factoria.coders.repositories.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class SeedDataService {


    private IProductRepository productRepository;
    private IUserRepository userRepository;
    private PasswordEncoder encoder;

    public SeedDataService(IProductRepository productRepository, IUserRepository userRepository, PasswordEncoder encoder) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void createData() {
        if (!productRepository.findAll().isEmpty()) return;

        var user = userRepository.findById(1L).get();
        var product = new Product();
        product.setAuthor(user);
        product.setImg("https://cdn.pixabay.com/photo/2016/10/09/10/43/plan-1725510_1280.jpg");
        product.setName("testProduct");
        productRepository.save(product);
    }

}
