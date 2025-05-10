package com.mohsenko.e_commerce.service;

import com.mohsenko.e_commerce.dto.brand.*;
import com.mohsenko.e_commerce.dto.pagination.PageResponse;
import com.mohsenko.e_commerce.dto.pagination.SliceResponse;
import com.mohsenko.e_commerce.entity.Brand;
import com.mohsenko.e_commerce.exception.ObjectAlreadyExistsException;
import com.mohsenko.e_commerce.exception.ResourceNotFoundException;
import com.mohsenko.e_commerce.mapper.BrandMapper;
import com.mohsenko.e_commerce.mapper.PageMapper;
import com.mohsenko.e_commerce.repository.BrandRepo;
import com.mohsenko.e_commerce.specification.BrandSpecifications;
import com.mohsenko.e_commerce.utils.SlugUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements IBrandService {
    private final BrandRepo brandRepo;
    private final  BrandMapper brandMapper;
    private final PageMapper pageMapper;

    @Override
    public SliceResponse<BrandBaseDto> getAllActiveBrand(int page, int size) {
        // fetch active brands from db
        Pageable pageable = PageRequest.of(page, size, Sort.by("slug"));
        Slice<Brand> activeBrands = brandRepo.findAllByActive(true, pageable);

        // map entities to its dto
        Slice<BrandBaseDto> brandDtos  = activeBrands.map(brandMapper::toBrandBaseDto);
        return pageMapper.toSliceResponse(brandDtos);
    }

    @Override
    public BrandDetailsDto getActiveBrand(String slug) {
        // fetch the brand
        Brand brand = brandRepo.findBySlugAndActiveTrue(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "slug", slug));

        // map and return
        return brandMapper.toBrandDetailsDto(brand);
    }

    @Override
    public PageResponse<BrandBaseAdminDto> getAllBrands(String search, Boolean active, Pageable pageable) {
        // create a new specification
        Specification<Brand> spec = Specification.where(null);

        // check potential specifications
        if (search != null && !search.isBlank())
            spec = spec.and(BrandSpecifications.hasNameLike(search));

        if (active != null)
            spec = spec.and(BrandSpecifications.hasActiveStatus(active));

        // find the brands &  map entity to dto  // TODO: exaplin this mapping, I don't understand it
        Page<BrandBaseAdminDto> brands = brandRepo.findAll(spec, pageable).map(brandMapper::toBrandBaseAdminDto);

        // reduce pagination response
        return pageMapper.toPageResponse(brands);
    }

    @Override
    public BrandDetailsAdminDto getBrand(int id) {
        // fetch the brand
        Brand brand = brandRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id));

        // map and return
        return brandMapper.toBrandDetailsAdminDto(brand);
    }

    @Override
    public void createBrand(BrandRequestDto dto) {
        // check if the brand already exists
        if (brandRepo.existsBySlug(SlugUtil.toSlug(dto.getName()))) {
            throw new ObjectAlreadyExistsException("Brand", "name", dto.getName());
        }

        // map and save
        brandRepo.save(brandMapper.toBrand(dto, new Brand()));
    }

    @Override
    public void updateBrand(int id, BrandRequestDto dto) {
        // check the brand exists
        Brand brand = brandRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id));

        // check new brand name [if new] not conflict with another brand name
        Optional<Brand> brandConflict = brandRepo.findBySlug((SlugUtil.toSlug(dto.getName())));
        if (brandConflict.isPresent() && brandConflict.get().getId() != id) {
            throw new ObjectAlreadyExistsException("Brand", "name", dto.getName());
        }

        // map and update
        brandRepo.save(brandMapper.toBrand(dto, brand));
    }

    @Override
    public void toggleBrandStatus(int id) {
        // get the brand
        Brand brand = brandRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id));

        // toggle status
        brand.setActive(!brand.isActive());
        brandRepo.save(brand);
    }

    @Override
    public void deleteBrand(List<Integer> ids) {
        brandRepo.deleteAllByIdInBatch(ids);

    }




}
