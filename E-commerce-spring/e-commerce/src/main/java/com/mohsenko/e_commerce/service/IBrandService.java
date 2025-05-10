package com.mohsenko.e_commerce.service;

import com.mohsenko.e_commerce.dto.brand.*;
import com.mohsenko.e_commerce.dto.pagination.PageResponse;
import com.mohsenko.e_commerce.dto.pagination.SliceResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBrandService {
    SliceResponse<BrandBaseDto> getAllActiveBrand(int page, int size);
    BrandDetailsDto getActiveBrand(String slug);

    PageResponse<BrandBaseAdminDto> getAllBrands(String search, Boolean active, Pageable pageable);
    BrandDetailsAdminDto getBrand(int id);

    void createBrand(BrandRequestDto dto);
    void updateBrand(int id, BrandRequestDto dto);
    void deleteBrand(List<Integer> ids);

    void toggleBrandStatus(int id);
}