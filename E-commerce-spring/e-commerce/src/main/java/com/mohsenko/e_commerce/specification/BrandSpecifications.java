package com.mohsenko.e_commerce.specification;

import com.mohsenko.e_commerce.entity.Brand;
import org.springframework.data.jpa.domain.Specification;

public class BrandSpecifications {
    public static Specification<Brand> hasNameLike(String keyword) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + keyword.trim().replaceAll(" +", " ").toLowerCase() + "%");
    }

    public static Specification<Brand> hasActiveStatus(Boolean status) {
        return (root, query, cb) -> cb.equal(root.get("active"), status);
    }
}
