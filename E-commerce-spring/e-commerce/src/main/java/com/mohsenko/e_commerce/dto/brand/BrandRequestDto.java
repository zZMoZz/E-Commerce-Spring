package com.mohsenko.e_commerce.dto.brand;

import com.mohsenko.e_commerce.constant.AppConstants;
import com.mohsenko.e_commerce.validation.TrimmedSize;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BrandRequestDto {
    @NotBlank(message = "Name is required")
    @TrimmedSize(min = 2, max = 255, fieldName = "Name")
    private String name;

    @TrimmedSize(min= 10, max = 500, fieldName = "Description")
    private String description;

    private String logoUrl;

    private boolean active;
}
