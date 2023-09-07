package com.sara.app.exception;

public class ProductExceptionNotFound extends RuntimeException {

    public ProductExceptionNotFound(String exception){
        super(exception);
    }
}
