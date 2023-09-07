package com.sara.app.service.impl;

import ch.qos.logback.classic.Logger;
import com.sara.app.dto.ProductDetail;
import com.sara.app.exception.ProductExceptionNotFound;
import com.sara.app.service.IMockService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MockServiceImpl implements IMockService {

    Logger logger = (Logger) LoggerFactory.getLogger(MockServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    private static String URL_MOCK_SERVICE = "http://localhost:3001/product/";
    private static String PATH_SERVICE = "/similarids";


    @Override
    public List getSimilarIds(String id) {

        try {
            //return restTemplate.getForObject(URL_MOCK_SERVICE + id + PATH_SERVICE, List.class);
            ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL_MOCK_SERVICE + id + PATH_SERVICE, List.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()){
                return responseEntity.getBody();
            }else {
                throw new ProductExceptionNotFound("Ids Not Found");
            }
        } catch (Exception e) {
            logger.error("Error get similar id "+ e.getMessage());
            throw new ProductExceptionNotFound("Ids Not Found");
        }
    }

    @Override
    public ProductDetail getProductDetail(String id) {

        try {
            //return restTemplate.getForObject(URL_MOCK_SERVICE + id, ProductDetail.class);
            ResponseEntity<ProductDetail> responseEntity = restTemplate.getForEntity(URL_MOCK_SERVICE + id, ProductDetail.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()){
                return responseEntity.getBody();
            }else {
                throw new ProductExceptionNotFound("Product Not Found");
            }
        } catch (Exception e) {
            logger.error("Error get product detail "+e.getMessage());
            throw new ProductExceptionNotFound("Product Not Found");
        }
    }

}
