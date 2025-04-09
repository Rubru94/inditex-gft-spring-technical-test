package com.inditex.technical_test.price.application.port.in;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.technical_test.price.domain.Price;

public interface PriceQueryUseCase {
    Optional<Price> getPrice(Integer brandId, Integer productId, LocalDateTime applicationDate);
}
