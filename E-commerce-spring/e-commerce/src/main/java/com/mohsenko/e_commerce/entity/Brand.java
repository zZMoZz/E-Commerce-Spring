package com.mohsenko.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Brand")
public class Brand extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "logo_url")
    private String logoUrl;

    @Builder.Default
    @Column(name = "active")
    private boolean active = true;

    @Column(name = "slug", nullable = false, unique = true)
    private String slug;
}
