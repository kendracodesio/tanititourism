package com.kendrareynolds.tanititourism.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "places_to_stay")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceToStay extends Listing {

    public PlaceToStay(String name, String description, String phone, Cost cost, String imageUrl, String imageAltText,  Region region, ListingType listingType, StayType stayType) {
        super(name, description, phone, cost, imageUrl, imageAltText, region);
        this.listingType = listingType;
        this.stayType = stayType;
    }

    private ListingType listingType;


    @Override
    public ListingType getListingType() {
        return this.listingType = ListingType.STAY;
    }

    @ManyToOne
    @JoinColumn(name = "stay_type_id")
    private StayType stayType;


    @Override
    public String toString() {
        return "PlaceToStay{" +
                "name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", phone='" + super.getPhone() + '\'' +
                ", cost=" + super.getCost() +
                ", region=" + super.getRegion() +
                "stayType=" + stayType +
                '}';
    }
}
