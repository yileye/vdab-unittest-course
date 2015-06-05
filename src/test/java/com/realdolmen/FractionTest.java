package com.realdolmen;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FractionTest {
    @Test(expected = ArithmeticException.class)
    public void denominatorMustNotBeZero() throws Exception {
        new Fraction(1, 0);
    }

    @Test
    public void fractionIsAutoSimplified() throws Exception {
        Fraction f = new Fraction(3 * 3 * 5, 3 * 5 * 7);
        Assert.assertEquals(3, f.numerator());
        Assert.assertEquals(7, f.denominator());
    }

    @Test
    public void fractionIsSimplified() throws Exception {
        Fraction f = new Fraction(2, 10);
        assertEquals(1, f.numerator());
        assertEquals(5, f.denominator());
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
    public void equalsReturnsTrueIfBothAreEqual() throws Exception {
        assertEquals(new Fraction(37, 144), new Fraction(37*7, 144*7));
    }

    @Test
    public void equalsReturnsFalseIfBothAreNotEqual() throws Exception {
        assertNotEquals(new Fraction(1, 5), new Fraction(2, 7));
    }

    @Test
    public void equalsReturnsTrueIfBothSimplifyToSameValue() throws Exception {
        assertEquals(new Fraction(1, 2), new Fraction(2, 4));
    }

    @Test
    public void equalsReturnsFalseIfClassIsNotTheSame() throws Exception {
        assertNotEquals(new Fraction(1, 2), "Ceci n'est pas une Fraction");
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
