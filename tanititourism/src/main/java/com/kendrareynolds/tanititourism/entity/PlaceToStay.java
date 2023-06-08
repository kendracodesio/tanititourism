package com.kendrareynolds.tanititourism.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @ManyToOne
    @JoinColumn(name = "stay_type_id")
    private StayType stayType;
}
