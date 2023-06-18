package com.kendrareynolds.tanititourism.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Cost {
    BUDGET("$"),
    ECONOMICAL("$$"),
    MID_RANGE("$$$"),
    LUXURY("$$$$");

    private String label;

    Cost(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return this.label;
    }

    public String getName() {
        return this.name();
    }

    @JsonCreator
    public static Cost fromName(String name) {
        for (Cost cost : Cost.values()) {
            if (cost.getName().equalsIgnoreCase(name)) {
                return cost;
            }
        }
        throw new IllegalArgumentException("Invalid cost name: " + name);
    }

}

