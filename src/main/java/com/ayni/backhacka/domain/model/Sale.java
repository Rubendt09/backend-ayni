package com.ayni.backhacka.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection="sales")
public class Sale {
    @Id private String id;
    @DBRef private Client client;
    @DBRef private Product product;
    private int quantity;
    private Date date;
    private double totalAmount;
}