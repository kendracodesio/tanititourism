package com.kendrareynolds.tanititourism.entity;

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
}
