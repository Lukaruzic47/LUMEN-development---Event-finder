package com.evento.app.web.dto;


public record LoginResponseDto(String token, Long expiresIn) {
}
