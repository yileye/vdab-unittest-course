package com.realdolmen;

import static java.lang.Math.*;

public class Utilities {
    /**
     * Calculates the "greatest common factor" (GCF) or "greatest common divisor" (GCD) of two integers (Z).
     * @param a First integer.
     * @param b Second integer.
     * @return The greatest common factor of the two specified integers.
     */
    public static int greatestCommonFactor(int a, int b) {
        if(a == 0 && b == 0) {
            throw new ArithmeticException("GCF(0, 0) is undefined");
        }

        if(a == 0 || b == 0) {
            return 1;
        }

        a = abs(a);
        b = abs(b);
        if(a < b) {
            return greatestCommonFactor(b, a);
        }

        int delta = a % b;
        if(delta == 0) {
            return b;
        } else {
            return greatestCommonFactor(b, delta);
        }
    }
}
