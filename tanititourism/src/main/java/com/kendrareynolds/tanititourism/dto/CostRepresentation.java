package com.kendrareynolds.tanititourism.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CostRepresentation {
    private String name;
    private String label;

    public CostRepresentation(String name, String label) {
        this.name = name;
        this.label = label;
    }
}
