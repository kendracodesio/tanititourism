package com.kendrareynolds.tanititourism.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stay_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StayType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type_name", unique = true)
    private String typeName;


}
