package com.ayni.backhacka.infraestructure.adapters.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductRequest {
    private String name;
    private int quantity;
    private String unitOfMeasure;
    private double cost;
    private double price;
    private String category;
    private String activeIngredient;

}
