package com.inditex.technical_test.price.unit.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inditex.technical_test.core.error.exceptions.NotFoundException;
import com.inditex.technical_test.price.application.port.out.PriceRepositoryPort;
import com.inditex.technical_test.price.application.service.PriceService;
import com.inditex.technical_test.price.domain.Price;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceService priceService;

    private Price price;

    @BeforeEach
    void setUp() {
        price = new Price();
    }

    @Nested
    class GetPriceTests {
        Integer brandId = 1;
        Integer productId = 35455;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        @Test
        void shouldReturnPrice_WhenPriceExists() {

            when(priceRepositoryPort.findPrice(brandId, productId, date)).thenReturn(Optional.of(price));

            Optional<Price> result = priceService.getPrice(brandId, productId, date);

            assertTrue(result.isPresent());
            assertEquals(price.getPrice(), result.get().getPrice());
            verify(priceRepositoryPort, times(1)).findPrice(brandId, productId, date);
        }

        @Test
        void shouldThrowNotFoundException_WhenPriceDoesNotExist() {

            when(priceRepositoryPort.findPrice(brandId, productId, date)).thenReturn(Optional.empty());

            NotFoundException exception = assertThrows(NotFoundException.class,
                    () -> priceService.getPrice(brandId, productId, date));

            assertEquals("No price found for product: " + productId + " & brand: " + brandId + " at date: "
                    + date, exception.getMessage());
            verify(priceRepositoryPort, times(1)).findPrice(brandId, productId, date);
        }
    }

}
