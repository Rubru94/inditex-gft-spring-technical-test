package com.inditex.technical_test.core.error;

import java.time.LocalDateTime;

public record ErrorDetailRecord(LocalDateTime timestamp, String message, String details) {

    /**
     * @see · Default constructor is created automatically if defined as record. -->
     *      public ErrorDetail(LocalDateTime timestamp, String message, String details)
     */

    public ErrorDetailRecord(String message, String details) {
        this(LocalDateTime.now(), message, details);
    }
}
