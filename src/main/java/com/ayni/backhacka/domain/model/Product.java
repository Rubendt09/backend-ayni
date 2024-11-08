package com.ayni.backhacka.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="products")
public class Product {
    @Id private String id;
    private String name;
    private int quantity;
    private String unitOfMeasure;
    private double cost;
    private double price;
    private String category;
    private double totalPrice;
    private double totalCost;
    private double totalProfit;
}
