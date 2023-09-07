package com.sara.app.service;


import com.sara.app.dto.ProductDetail;

import java.util.List;

public interface IMockService {
    
    public List<Integer> getSimilarIds(String id);
    public ProductDetail getProductDetail(String id);
}
