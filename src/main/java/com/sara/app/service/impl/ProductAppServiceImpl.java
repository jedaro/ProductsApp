package com.sara.app.service.impl;

import com.sara.app.dto.ProductDetail;
import com.sara.app.exception.ProductExceptionNotFound;
import com.sara.app.service.IMockService;
import com.sara.app.service.IProductAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductAppServiceImpl implements IProductAppService {

    @Autowired
    IMockService iMockService;

    Logger logger = LoggerFactory.getLogger(ProductAppServiceImpl.class);

    @Override
    public List<ProductDetail> getProductSimilar(String productId) {

        try {
            List<ProductDetail> products = new ArrayList<>();

            List<Integer> ids = iMockService.getSimilarIds(productId);

            ids.forEach((id) -> {
                ProductDetail product = iMockService.getProductDetail(String.valueOf(id));
                products.add(product);
            });

            return products;
        } catch (Exception e) {
            logger.error("ProductsAppServiceImpl error getting similar items");
            throw new ProductExceptionNotFound("Product Not Found");
        }

    }
}
