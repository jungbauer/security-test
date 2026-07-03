package com.jungbauer.securitytest.model.dto;

import lombok.Data;

@Data
public class RestResponseDTO {
    private String message;
    private Integer count;

    public RestResponseDTO(String message, Integer count) {
        this.message = message;
        this.count = count;
    }
}
