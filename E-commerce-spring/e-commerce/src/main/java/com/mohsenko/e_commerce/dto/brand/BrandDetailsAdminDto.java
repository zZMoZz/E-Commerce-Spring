package com.mohsenko.e_commerce.dto.brand;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BrandDetailsAdminDto extends BrandBaseAdminDto{
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
