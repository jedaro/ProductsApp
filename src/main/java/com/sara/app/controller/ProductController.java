package com.sara.app.controller;


import com.sara.app.exception.ProductExceptionNotFound;
import com.sara.app.service.IProductAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/")
class ProductController {

    @Autowired
    IProductAppService iProductsAppService;

    @GetMapping("/product/{productId}/similar")
    public ResponseEntity<List> getProductSimilar(@PathVariable(value = "productId", required = true) String productId){
        
        try {
            return new ResponseEntity<List>(iProductsAppService.getProductSimilar(productId), HttpStatus.OK);
        } catch (ProductExceptionNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }
    
}