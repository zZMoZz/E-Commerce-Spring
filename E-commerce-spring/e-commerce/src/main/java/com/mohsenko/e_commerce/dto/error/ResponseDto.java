package com.mohsenko.e_commerce.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String StatusCode;
    private String StatusMessage;
}
