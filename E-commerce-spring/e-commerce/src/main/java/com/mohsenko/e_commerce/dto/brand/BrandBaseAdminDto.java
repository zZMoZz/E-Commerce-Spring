package com.mohsenko.e_commerce.dto.brand;

import lombok.Data;

@Data
public class BrandBaseAdminDto extends BrandBaseDto {
    private int id;
    private boolean active;
}
