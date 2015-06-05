package com.realdolmen;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if(denominator == 0) throw new ArithmeticException("Denominator must not be zero");

        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    private void simplify() {
        int gcf = Utilities.greatestCommonFactor(numerator, denominator);
        numerator = numerator / gcf;
        denominator = denominator / gcf;
    }

    public int numerator() {
        return numerator;
    }

    public int denominator() {
        return denominator;
    }

    @Override
    public boolean equals(Object o) {
        Fraction that = (Fraction)o;
        return this.denominator == that.denominator && this.numerator == that.numerator;
    }

    @Override
    public String toString() {
        return numerator + " / " + denominator;
    }

    public double asDouble() {
        return ((double)numerator) / denominator;
    }
}
