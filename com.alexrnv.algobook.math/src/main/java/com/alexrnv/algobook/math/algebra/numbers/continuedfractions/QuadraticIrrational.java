package com.alexrnv.algobook.math.algebra.numbers.continuedfractions;

import com.alexrnv.algobook.math.algebra.fundamentals.factorization.GCD;
import com.alexrnv.algobook.math.algebra.numbers.poly.Square;

import java.util.Objects;

/**
 * For detailed description, please check https://en.wikipedia.org/wiki/Quadratic_irrational_number.
 * Quadratic irrational numbers are always stored in canonical form: (a + b * sqrt(c)) / d,
 * with b, c and d non-zero, and with c not being a perfect square.
 */
public class QuadraticIrrational {
    protected final long a;
    protected final long b;
    protected final long c;
    protected final long d;

    protected QuadraticIrrational(long a, long b, long c, long d) {
        if (b == 0)
            throw new IllegalArgumentException("b parameter must not be 0");

        if (d == 0)
            throw new IllegalArgumentException("d parameter must not be 0");

        if (Square.isSquareNumber(c))
            throw new IllegalArgumentException("c parameter must not be a perfect square");

        long gcd = GCD.gcd(a,b,d);
        this.a = a / gcd;
        this.b = b / gcd;
        this.c = c;
        this.d = d / gcd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuadraticIrrational that = (QuadraticIrrational) o;
        return a == that.a &&
                b == that.b &&
                c == that.c &&
                d == that.d;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }

    @Override
    public String toString() {
        return String.format("(%d+%d%s%d)/%d", a,b,'\u221A',c,d);
    }
}
