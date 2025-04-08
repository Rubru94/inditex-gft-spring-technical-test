package com.inditex.technical_test.price.infrastructure.adapter.in;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.technical_test.price.application.port.in.PriceQueryUseCase;
import com.inditex.technical_test.price.domain.Price;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceQueryUseCase priceQueryUseCase;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public PriceController(PriceQueryUseCase priceQueryUseCase) {
        this.priceQueryUseCase = priceQueryUseCase;
    }

    @GetMapping
    public ResponseEntity<Price> getPrice(
            @RequestParam int brandId,
            @RequestParam int productId,
            @RequestParam String applicationDate) {

        LocalDateTime dateTime = LocalDateTime.parse(applicationDate, formatter);
        Optional<Price> price = priceQueryUseCase.getPrice(brandId, productId, dateTime);

        return price.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
