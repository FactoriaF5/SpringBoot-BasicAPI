package com.factoria.coders;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
}
