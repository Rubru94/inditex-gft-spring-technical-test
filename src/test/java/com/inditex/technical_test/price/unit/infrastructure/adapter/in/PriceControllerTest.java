package com.inditex.technical_test.price.unit.infrastructure.adapter.in;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.inditex.technical_test.core.constants.DateTimeConstants;
import com.inditex.technical_test.price.application.port.in.PriceQueryUseCase;
import com.inditex.technical_test.price.domain.Price;
import com.inditex.technical_test.price.infrastructure.adapter.in.PriceController;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PriceQueryUseCase priceQueryUseCase;

    @Nested
    class GetPriceTests {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeConstants.DATE_TIME_PATTERN);
        Integer brandId = 1;
        Integer productId = 35455;
        String applicationDate = "2020-06-14 10:00:00";

        @Test
        void shouldReturnPrice_WhenValidRequest() throws Exception {

            LocalDateTime dateTime = LocalDateTime.parse(applicationDate, formatter);
            Price price = new Price();

            when(priceQueryUseCase.getPrice(brandId, productId, dateTime)).thenReturn(Optional.of(price));

            mockMvc.perform(get("/api/prices")
                    .param("brandId", brandId.toString())
                    .param("productId", productId.toString())
                    .param("applicationDate", applicationDate)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            verify(priceQueryUseCase, times(1)).getPrice(brandId, productId, dateTime);
        }

        @Test
        void shouldReturnBadRequest_WhenInvalidDateFormat() throws Exception {

            mockMvc.perform(get("/api/prices")
                    .param("brandId", brandId.toString())
                    .param("productId", productId.toString())
                    .param("applicationDate", "invalid-date")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
            verify(priceQueryUseCase, never()).getPrice(anyInt(), anyInt(), any(LocalDateTime.class));
        }

        @Test
        void shouldReturnBadRequest_WhenInvalidMethodArgument() throws Exception {

            mockMvc.perform(get("/api/prices")
                    .param("brandId", brandId.toString() + "-invalid")
                    .param("productId", productId.toString())
                    .param("applicationDate", applicationDate)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
            verify(priceQueryUseCase, never()).getPrice(anyInt(), anyInt(), any(LocalDateTime.class));
        }
    }
}
