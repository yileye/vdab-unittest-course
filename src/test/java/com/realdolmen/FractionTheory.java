package com.realdolmen;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class FractionTheory {
    @DataPoints
    public static int[] data = fillData();

    private static int[] fillData() {
        int bottom = -1000;
        int top = 1000;
        int[] data = new int[top - bottom];
        for(int i=bottom; i<top; i++) {
            data[i] = i;
        }
        return data;
    }

    @Theory
    public void reciprocalSwapsNumeratorAndDenominator(int n, int d) {
        Fraction f = new Fraction(n, d);
        Fraction g = f.reciprocal();
        System.out.println("reciprocal(" + f + ") = " + g);
        assertEquals(d, g.numerator());
        assertEquals(n, g.denominator());
    }
}
