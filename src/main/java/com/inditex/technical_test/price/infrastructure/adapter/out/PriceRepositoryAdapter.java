package com.inditex.technical_test.price.infrastructure.adapter.out;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.inditex.technical_test.price.application.port.out.PriceRepositoryPort;
import com.inditex.technical_test.price.domain.Price;
import com.inditex.technical_test.price.infrastructure.repository.PriceRepository;

@Repository
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository priceRepository;

    public PriceRepositoryAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<Price> findPrice(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        return priceRepository.findPrice(brandId, productId, applicationDate);
    }
}
