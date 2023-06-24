package com.kendrareynolds.tanititourism.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Listing {

    public Listing(String name, String description, String phone, Cost cost, String imageUrl, String imageAltText, Region region) {
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.cost = cost;
        this.imageUrl = imageUrl;
        this.imageAltText = imageAltText;
        this.region = region;
    }

    public enum ListingType {
        DO, STAY, DINE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description", length = 1000)
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
                ", region=" + region +
                '}';
    }
}


