package com.ayni.backhacka.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="clients")
public class Client {
    @Id private String id;
    private String customerSegment;
    private String name;
    private String clientType;
    private String region;
    private String city;
    private String zone;
    private String contact;
}