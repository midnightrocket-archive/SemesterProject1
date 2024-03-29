package com.example.semester1.core.Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ApplianceTest {

    @Test
    @DisplayName("Test Appliance - HasAlias")
    public void testHasAlias() {
        Appliance appliance1 = new Appliance("testId1", "testDisplayname", "activityId");
        assertFalse(appliance1.hasAlias("testId??"));
        assertTrue(appliance1.hasAlias("testId1"));
        assertTrue(appliance1.hasAlias("testDisplayname"));
    }
}