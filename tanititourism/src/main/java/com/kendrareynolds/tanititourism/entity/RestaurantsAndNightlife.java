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

    public enum AcceptsReservations {
        YES, NO
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "accepts_reservations")
    private AcceptsReservations acceptsReservations;

    @ManyToOne
    @JoinColumn(name = "dine_type_id")
    private  DineType dineType;
}
