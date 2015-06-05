package com.realdolmen;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assume.*;
import static org.mockito.Matchers.*;

public class UtilitiesTest {
    @Test
    public void greatestCommonFactorReturnsGreatestCommonFactorForNormalValues() {
        assertEquals(8, Utilities.greatestCommonFactor(24, 16));
        assertEquals(4, Utilities.greatestCommonFactor(48, 100));
    }

    @Test
    public void greatestCommonFactorReturnsOneIfAnyParameterIsZero() {
        assertEquals(1, Utilities.greatestCommonFactor(0, 2));
        assertEquals(1, Utilities.greatestCommonFactor(10, 0));
    }

    @Test
    public void greatestCommonFactorAlwaysReturnsPositiveValues() throws Exception {
        assertEquals(2, Utilities.greatestCommonFactor(2, 4));
        assertEquals(2, Utilities.greatestCommonFactor(-2, 4));
        assertEquals(2, Utilities.greatestCommonFactor(2, -4));
        assertEquals(2, Utilities.greatestCommonFactor(-2, -4));
    }

    @Test
    public void greatestCommonFactorOfTwoEqualNumbersEqualsTheNumberItself() throws Exception {
        assertEquals(28, Utilities.greatestCommonFactor(28, 28));
    }

    @Test
    @Ignore("Uncool test is disbled")
    public void greatestCommonFactorOfZeroAndZeroIsUndefined() throws Exception {
        try {
            Utilities.greatestCommonFactor(0, 0);
            Assert.fail("Exception should have been thrown");
        } catch(ArithmeticException e) {
            assertEquals("GCF(0, 0) is undefined", e.getMessage());
        }
        double d = 1.0 / 3.0;
    }
}
