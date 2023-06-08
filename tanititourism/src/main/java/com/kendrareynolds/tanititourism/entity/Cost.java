package com.kendrareynolds.tanititourism.entity;

public enum Cost {
    BUDGET("$"),
    ECONOMICAL("$$"),
    MID_RANGE("$$$"),
    LUXURY("$$$$");

    private String label;

    Cost(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
