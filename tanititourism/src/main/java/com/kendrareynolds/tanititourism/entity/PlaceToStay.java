package com.kendrareynolds.tanititourism.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "places_to_stay")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceToStay extends Listing {


    @Override
    public ListingType getListingType() {
        return ListingType.STAY;

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
