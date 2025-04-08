package com.inditex.technical_test.price.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.inditex.technical_test.price.application.port.in.PriceQueryUseCase;
import com.inditex.technical_test.price.application.port.out.PriceRepositoryPort;
import com.inditex.technical_test.price.domain.Price;

@Service
public class PriceService implements PriceQueryUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Optional<Price> getPrice(int brandId, int productId, LocalDateTime applicationDate) {
        return priceRepositoryPort.findPrice(brandId, productId, applicationDate);
    }
}
