package com.mohsenko.e_commerce.repository;

import com.mohsenko.e_commerce.entity.Brand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand> {
    Slice<Brand> findAllByActive(boolean status, Pageable pageable);

    Optional<Brand> findBySlugAndActiveTrue(String slug);
    Optional<Brand> findBySlug(String slug);

    boolean existsBySlug(String slug);
}
