package com.sara.app;


import com.sara.app.exception.ProductExceptionNotFound;
import com.sara.app.service.impl.MockServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestMockServiceImpl {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    MockServiceImpl mockService = new MockServiceImpl();

    @Test
    @DisplayName("Get similar ids")
    @Disabled
    void testGetSimilarIdsOk(){
        // given
        List<Integer> listIds = Arrays.asList(1);
        ResponseEntity responseEntity = new ResponseEntity<>(listIds, HttpStatus.OK);
        // when
        when(restTemplate.getForEntity("http://localhost:3001/foo", List.class)).thenReturn(responseEntity);

        // then
        List<Integer> idsProducts = mockService.getSimilarIds("1");
        assertNotNull(idsProducts.get(0));

    }

    @Test
    @DisplayName("Get similar ids not found")
    @Disabled
    void testGetSimilarIdsNotFound(){

        // given

        // when
        given(restTemplate.getForObject("http://localhost/", List.class)).willThrow(new Exception());
        // then
        assertThrows(ProductExceptionNotFound.class, () -> mockService.getSimilarIds("1"));

    }

}
