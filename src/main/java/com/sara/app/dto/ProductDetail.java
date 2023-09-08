package com.sara.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
    private String id;
    private String name;
    private Integer price;
    private Boolean availability;
    
    
}
