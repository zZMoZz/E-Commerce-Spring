package com.mohsenko.e_commerce.mapper;

import com.mohsenko.e_commerce.dto.brand.*;
import com.mohsenko.e_commerce.dto.pagination.PageResponse;
import com.mohsenko.e_commerce.dto.pagination.SliceResponse;
import com.mohsenko.e_commerce.entity.Brand;
import com.mohsenko.e_commerce.utils.SlugUtil;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;


// this annotation allows you to inject the mapper as a spring bean
@Mapper(componentModel = "spring")
public abstract class BrandMapper {

    public abstract BrandBaseDto toBrandBaseDto(Brand brand);
    public abstract BrandBaseAdminDto toBrandBaseAdminDto(Brand brand);
    public abstract BrandDetailsDto toBrandDetailsDto(Brand brand);
    public abstract BrandDetailsAdminDto toBrandDetailsAdminDto(Brand brand);

    public Brand toBrand(BrandRequestDto dto, Brand brand) {
        brand.setName(dto.getName().trim()); // remove spaces from edges, we already handle that using "TrimmedSize"
        brand.setDescription(dto.getDescription());
        brand.setLogoUrl(dto.getLogoUrl()); // TODO:add logic to make sure that image path correct
        brand.setActive(dto.isActive());
        brand.setSlug(SlugUtil.toSlug(dto.getName()));
        return brand;
    }
}
