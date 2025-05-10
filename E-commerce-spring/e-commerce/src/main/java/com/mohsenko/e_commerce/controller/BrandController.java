package com.mohsenko.e_commerce.controller;

import com.mohsenko.e_commerce.dto.brand.*;
import com.mohsenko.e_commerce.dto.error.ResponseDto;
import com.mohsenko.e_commerce.dto.pagination.PageResponse;
import com.mohsenko.e_commerce.dto.pagination.SliceResponse;
import com.mohsenko.e_commerce.service.IBrandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class BrandController {
    private final IBrandService brandService;

    // Public endpoints
    @GetMapping("/brands")
    ResponseEntity<SliceResponse<BrandBaseDto>> getAllActiveBrands(
             @RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.getAllActiveBrand(page, Math.min(size, 50)));
    }

    @GetMapping("/brands/{slug}")
    ResponseEntity<BrandDetailsDto> getActiveBrand(@PathVariable String slug) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.getActiveBrand(slug));
    }

    // Admin endpoints
    @GetMapping("/admin/brands")
    ResponseEntity<PageResponse<BrandBaseAdminDto>> getAllBrands(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") @Pattern(regexp = "^(asc|desc)$", message = "Invalid sort value. Only 'asc' or 'desc' are allowed.") String sort) {
        // configure pagination settings
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, Math.min(size, 50), Sort.by(direction, "name"));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.getAllBrands(search, active, pageable));
    }

    @GetMapping("/admin/brands/{id}")
    ResponseEntity<BrandDetailsAdminDto> getBrand(@PathVariable @Positive int id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.getBrand(id));
    }

    @PostMapping("/admin/brands")
    ResponseEntity<ResponseDto> createBrand( @RequestBody @Valid BrandRequestDto dto) {
        brandService.createBrand(dto);
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto("201", "Brand created successfully"));
    }

    @PutMapping("/admin/brands/{id}")
    ResponseEntity<Void> updateBrand( @PathVariable int id, @RequestBody @Valid BrandRequestDto dto) {
        brandService.updateBrand(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/admin/brands/{id}/toggle-status")
    ResponseEntity<Void> toggleBrandStatus(@PathVariable int id) {
        brandService.toggleBrandStatus(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/admin/brands")
    ResponseEntity<Void> deleteBrand(@RequestBody List<Integer> ids) {
        brandService.deleteBrand(ids);
        return ResponseEntity.noContent().build();
    }
}
