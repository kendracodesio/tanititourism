package com.kendrareynolds.tanititourism.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurants_and_nightlife")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantsAndNightlife extends Listing {
    public RestaurantsAndNightlife(String name, String description, String phone, Cost cost, String imageUrl, String imageAltText, Region region, ListingType listingType, AcceptsReservations acceptsReservations, DineType dineType) {
        super(name, description, phone, cost, imageUrl, imageAltText, region);
        this.listingType = listingType;
        this.acceptsReservations = acceptsReservations;
        this.dineType = dineType;
    }

    private ListingType listingType;




    @Override
    public ListingType getListingType() {
        return this.listingType = ListingType.DINE;
    }

    public enum AcceptsReservations {
        YES, NO
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "accepts_reservations")
    private AcceptsReservations acceptsReservations;


    @ManyToOne
    @JoinColumn(name = "dine_type_id")
    private DineType dineType;


    @Override
    public String toString() {
        return "RestaurantsAndNightlife{" +
                "name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", phone='" + super.getPhone() + '\'' +
                ", cost=" + super.getCost() +
                ", region=" + super.getRegion() +
                ", acceptsReservations=" + acceptsReservations +
                ", dineType=" + dineType +
                '}';
    }
}

