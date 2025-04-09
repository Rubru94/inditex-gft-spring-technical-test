package com.inditex.technical_test.price.infrastructure.adapter.in;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.technical_test.core.constants.DateTimeConstants;
import com.inditex.technical_test.core.error.exceptions.BadRequestException;
import com.inditex.technical_test.price.application.port.in.PriceQueryUseCase;
import com.inditex.technical_test.price.domain.Price;
import com.inditex.technical_test.price.domain.PriceRequestParamDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceQueryUseCase priceQueryUseCase;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeConstants.DATE_TIME_PATTERN);

    public PriceController(PriceQueryUseCase priceQueryUseCase) {
        this.priceQueryUseCase = priceQueryUseCase;
    }

    @GetMapping
    public ResponseEntity<Price> getPrice(@ModelAttribute @Valid PriceRequestParamDto priceRequestParamDto) {

        try {
            LocalDateTime dateTime = LocalDateTime.parse(priceRequestParamDto.getApplicationDate(), formatter);
            Optional<Price> price = priceQueryUseCase.getPrice(priceRequestParamDto.getBrandId(),
                    priceRequestParamDto.getProductId(), dateTime);

            return ResponseEntity.ok(price.get());
        } catch (DateTimeParseException e) {
            throw new BadRequestException(
                    "Invalid date format. Expected format is '" + DateTimeConstants.DATE_TIME_PATTERN + "'");
        }
    }
}
