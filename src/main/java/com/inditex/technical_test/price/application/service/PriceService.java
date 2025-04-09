package com.inditex.technical_test.price.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inditex.technical_test.core.error.exceptions.NotFoundException;
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
    public Optional<Price> getPrice(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        Optional<Price> price = priceRepositoryPort.findPrice(brandId, productId, applicationDate);

        if (!price.isPresent())
            throw new NotFoundException(
                    ("No price found for product: " + productId + " & brand: " + brandId + " at date: "
                            + applicationDate));

        return price;
    }
}
