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

    public Fraction reciprocal() {
        return new Fraction(this.denominator, this.numerator);
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
        return o instanceof Fraction && equals((Fraction) o);
    }

    private boolean equals(Fraction that) {
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
