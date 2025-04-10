package com.inditex.technical_test.price.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class PriceRequestParamDto {

    @NotNull
    private final Integer brandId;

    @NotNull
    private final Integer productId;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$")
    private final String applicationDate;
}
