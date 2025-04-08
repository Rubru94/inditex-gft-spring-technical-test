package com.inditex.technical_test.price.application.port.out;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.technical_test.price.domain.Price;

public interface PriceRepositoryPort {
    Optional<Price> findPrice(int brandId, int productId, LocalDateTime applicationDate);
}
