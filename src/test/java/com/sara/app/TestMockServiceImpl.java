package com.sara.app;



import com.sara.app.exception.ProductExceptionNotFound;
import com.sara.app.service.impl.MockServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestMockServiceImpl {

    @InjectMocks
    MockServiceImpl mockService;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Get similar ids")
    void testGetSimilarIdsOk(){

        // given
        // when
        given(restTemplate.getForObject("http://localhost:3001/foo", List.class)).willReturn(List.of(1,2,3));

        // then
        List<Integer> idsProducts = mockService.getSimilarIds("1");
        assertNotNull(idsProducts.get(0));


    }

    @Test
    @DisplayName("Get similar ids not found")
    @Disabled
    void testGetSimilarIdsNotFound(){

        // given
        List<Integer> idsProducts = List.of();

        // when
        given(restTemplate.getForObject("http://localhost/", List.class)).willThrow(new Exception());
        // then
        assertThrows(ProductExceptionNotFound.class, () -> mockService.getSimilarIds("1"));

    }

}
