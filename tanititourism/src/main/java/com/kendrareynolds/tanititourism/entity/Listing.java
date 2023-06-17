package com.kendrareynolds.tanititourism.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Listing {

    public enum ListingType {
        DO, STAY, DINE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "cost")
    private Cost cost;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_alt_text")
    private String imageAltText;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;



    public abstract ListingType getListingType();

    @Override
    public String toString() {
        return "Listing{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", cost=" + cost +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageAltText='" + imageAltText + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", region=" + region +
                '}';
    }
}


