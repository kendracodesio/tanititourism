package com.kendrareynolds.tanititourism.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionReportTest {

    @Test
    public void equalsTest() {
        ActionReport report1 = new ActionReport();
        report1.setId(1L);

        ActionReport report2 = new ActionReport();
        report2.setId(1L);

        ActionReport report3 = new ActionReport();
        report3.setId(2L);

        assertTrue(report1.equals(report2));
        assertFalse(report1.equals(report3));
    }
}
