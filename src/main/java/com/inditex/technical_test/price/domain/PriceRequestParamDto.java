package com.inditex.technical_test.price.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public final class PriceRequestParamDto {

    @NotNull
    private final Integer brandId;

    @NotNull
    private final Integer productId;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$")
    private final String applicationDate;

    public PriceRequestParamDto(Integer brandId, Integer productId, String applicationDate) {
        this.brandId = brandId;
        this.productId = productId;
        this.applicationDate = applicationDate;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getApplicationDate() {
        return applicationDate;
    }
}
