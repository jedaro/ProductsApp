package com.sara.app;

import com.sara.app.dto.ProductDetail;
import com.sara.app.exception.ProductExceptionNotFound;
import com.sara.app.service.impl.MockServiceImpl;
import com.sara.app.service.impl.ProductAppServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TestProductAppServiceImpl {

    @InjectMocks
    ProductAppServiceImpl productAppService;

    @Mock
    MockServiceImpl mockService;

    @Test
    @DisplayName("getProductSimilar ok")
    void getProductSimilarOk(){

        // give
        ProductDetail productDetail = ProductDetail.builder()
                .id("1")
                .name("Shirt")
                .price(9)
                .availability(true).build();

        // when
        given(mockService.getSimilarIds(anyString())).willReturn(List.of(1));
        given(mockService.getProductDetail(anyString())).willReturn(productDetail);

        // then
        List<ProductDetail> result = productAppService.getProductSimilar(productDetail.getId());

        Assertions.assertNotNull(result.get(0));

    }

    @Test
    @DisplayName("getProductSimilar Not found")
    void getProductSimilarNotFound(){

        // give
        ProductDetail productDetail = ProductDetail.builder()
                .id("1")
                .name("Shirt")
                .price(9)
                .availability(true).build();

        // when
        given(mockService.getSimilarIds(anyString())).willThrow(new ProductExceptionNotFound("Not found"));

        // then
        assertThrows(ProductExceptionNotFound.class, () -> productAppService.getProductSimilar("1"));

    }

}
