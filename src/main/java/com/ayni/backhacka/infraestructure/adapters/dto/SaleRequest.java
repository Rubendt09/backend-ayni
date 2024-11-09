package com.ayni.backhacka.infraestructure.adapters.dto;

import lombok.Data;

@Data
public class SaleRequest {
    private String clientId;
    private String productId;
    private int quantity;
}
