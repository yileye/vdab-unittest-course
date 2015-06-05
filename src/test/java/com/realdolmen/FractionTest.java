package com.realdolmen;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class FractionTest {
    @Test
    public void denominatorMustNotBeZero() throws Exception {
        try {
            new Fraction(1, 0);
            fail("Exception was expected");
        }catch (ArithmeticException e) {
            assertEquals("Denominator must not be zero", e.getMessage());
        }
    }

    @Test
    public void fractionIsAutoSimplified() throws Exception {
        Fraction f = new Fraction(3 * 3 * 5, 3 * 5 * 7);
        Assert.assertEquals(3, f.numerator());
        Assert.assertEquals(7, f.denominator());
    }

    @Test
    public void numeratorCanBeRetrieved() throws Exception {
        Fraction f = new Fraction(1, 2);
        assertEquals(1, f.numerator());
    }

    @Test
    public void denominatorCanBeRetrieved() throws Exception {
        Fraction f = new Fraction(1, 2);
        assertEquals(2, f.denominator());
    }

    @Test
    public void fractionIsSimplified() throws Exception {
        Fraction f = new Fraction(2, 10);
        assertEquals(1, f.numerator());
        assertEquals(5, f.denominator());
    }

    @Test
    public void equalsReturnsTrueIfBothAreEqual() throws Exception {
        Fraction a = new Fraction(37, 144);
        Fraction b = new Fraction(37*7, 144*7);
        assertEquals(a, b);
    }

    @Test
    public void equalsReturnsFalseIfBothAreNotEqual() throws Exception {
        Fraction a = new Fraction(1, 5);
        Fraction b = new Fraction(2, 7);
        assertNotEquals(a, b);
    }

    @Test
    public void equalsReturnsTrueIfBothSimplifyToSameValue() throws Exception {
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(2, 4);
        assertEquals(a, b);
    }

    @Test
    public void toStringDisplaysSlashedFOrm() throws Exception {
        assertEquals("5 / 7", new Fraction(5, 7).toString());
    }

    @Test
    public void asDoubleReturnsCalculatedForm() throws Exception {
        assertEquals(0.714285, new Fraction(5, 7).asDouble(), 0.000001);
    }
}
