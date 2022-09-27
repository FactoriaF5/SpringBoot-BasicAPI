package com.factoria.coders.integration.controllers;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.factoria.coders.auth.facade.IAuthenticationFacade;
import com.factoria.coders.controllers.ProductController;
import com.factoria.coders.dtos.ProductRequestDto;
import com.factoria.coders.models.Product;
import com.factoria.coders.models.Role;
import com.factoria.coders.models.User;
import com.factoria.coders.repositories.IProductRepository;
import com.factoria.coders.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    IProductService productService;

    @MockBean
    IAuthenticationFacade authenticationFacade;
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    ProductController productController;

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void allUsersCanGetAListOfProducts_GetAll() throws Exception {
        var product = new Product();
        product.setName("TestProduct");

        //Preparing Mock
        when(productService.getAll()).thenReturn(List.of(product));

        //Performing HTTP Request
        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("TestProduct")));
        //VERIFY Invoke method 1 time
        verify(productService, times(1)).getAll();


    }
    //MOCKING USER WITH ROLES
    @WithMockUser(roles = {"USER"})
    @Test
    void userWithUserRoleCanCreateProduct() throws Exception {

        Product product = new Product();
        ProductRequestDto productReq = new ProductRequestDto();
        productReq.setName("TestProduct");
        product.setName("TestProduct");
        //PREPARING MOCKS
        when(authenticationFacade.getAuthUser()).thenReturn(Optional.of(new User()));
        when(productService.createProduct(any(), any())).thenReturn(product);

        this.mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productReq)
                        )
        ).andExpect(status().isOk());
    }
}