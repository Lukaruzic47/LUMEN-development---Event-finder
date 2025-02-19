package com.evento.app.web.common;

import java.time.LocalDate;

public record ErrorResponse(String message, String details, String timestamp) {
    public ErrorResponse(String message, String details) {
        this(message, details, LocalDate.now().toString());
    }
}
