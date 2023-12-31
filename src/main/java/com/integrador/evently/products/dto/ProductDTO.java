package com.integrador.evently.products.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String shortDescription;
    private String description;
    private String location;
    private Long categoryId;
    private Long providerId;
}
