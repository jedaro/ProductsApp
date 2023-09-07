package com.sara.app.service;


import com.sara.app.dto.ProductDetail;

import java.util.List;

public interface IProductAppService {
    
    public List<ProductDetail> getProductSimilar(String productId);
}
