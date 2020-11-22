package com.alexrnv.algobook.math.algebra.numbers.fractions;

import java.math.BigInteger;
import java.util.Objects;

public class SimpleFraction implements Comparable<SimpleFraction> {
    private final BigInteger numerator;
    private final BigInteger denominator;

    public static SimpleFraction create(long numerator, long denominator)  {
        return create(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    public static SimpleFraction create(BigInteger numerator, BigInteger denominator) {
        return simplify(new SimpleFraction(numerator, denominator));
    }

    private SimpleFraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    private static SimpleFraction simplify(SimpleFraction fraction) {
        if (fraction.numerator.signum() < 0 && fraction.denominator.signum() < 0) {
            fraction = new SimpleFraction(fraction.numerator.negate(), fraction.denominator.negate());
        }
        BigInteger gcd = fraction.numerator.gcd(fraction.denominator);
        if (BigInteger.ZERO.equals(gcd)) {
            return new SimpleFraction(fraction.numerator, fraction.denominator);
        } else {
            return new SimpleFraction(fraction.numerator.divide(gcd), fraction.denominator.divide(gcd));
        }
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public boolean isZero() {
        return numerator.equals(BigInteger.ZERO) && !denominator.equals(BigInteger.ZERO);
    }

    public boolean isInfinity() {
        return !numerator.equals(BigInteger.ZERO) && denominator.equals(BigInteger.ZERO);
    }

    public boolean isUndefined() {
        return numerator.equals(BigInteger.ZERO) && denominator.equals(BigInteger.ZERO);
    }

    /**
     * a/b + c/d = (a*d + c*b) / bd
     */
    public SimpleFraction add(SimpleFraction another) {
        BigInteger newNumerator = this.numerator.multiply(another.denominator).add(this.denominator.multiply(another.numerator));
        BigInteger newDenominator = this.denominator.multiply(another.denominator);
        return create(newNumerator, newDenominator);
    }

    public SimpleFraction add(BigInteger number) {
        return add(create(number, BigInteger.ONE));
    }

    public SimpleFraction add(long number) {
        return add(create(number, 1L));
    }

    /**
     * a/b - c/d = (a*d - c*b) / bd
     */
    public SimpleFraction subtract(SimpleFraction another) {
        BigInteger newNumerator = this.numerator.multiply(another.denominator).subtract(this.denominator.multiply(another.numerator));
        BigInteger newDenominator = this.denominator.multiply(another.denominator);
        return create(newNumerator, newDenominator);
    }

    public SimpleFraction subtract(BigInteger number) {
        return subtract(create(number, BigInteger.ONE));
    }

    public SimpleFraction subtract(long number) {
        return subtract(create(number, 1L));
    }

    /**
     * a/b * c/d = a*c / b*d
     */
    public SimpleFraction multiply(SimpleFraction another) {
        BigInteger newNumerator = this.numerator.multiply(another.numerator);
        BigInteger newDenominator = this.denominator.multiply(another.denominator);
        return create(newNumerator, newDenominator);
    }

    public SimpleFraction multiply(BigInteger number) {
        return multiply(create(number, BigInteger.ONE));
    }

    public SimpleFraction multiply(long number) {
        return multiply(create(number, 1L));
    }

    /**
     * a/b / c/d = a*d / b*c
     */
    public SimpleFraction divide(SimpleFraction another) {
        BigInteger newNumerator = this.numerator.multiply(another.denominator);
        BigInteger newDenominator = this.denominator.multiply(another.numerator);
        return create(newNumerator, newDenominator);
    }

    public SimpleFraction divide(BigInteger number) {
        return divide(create(number, BigInteger.ONE));
    }

    public SimpleFraction divide(long number) {
        return divide(create(number, 1L));
    }

    /**
     * a/b => b/a
     * @return inverse of current fraction.
     */
    public SimpleFraction reciprocal() {
        return create(this.denominator, this.numerator);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleFraction that = (SimpleFraction) o;
        return numerator.equals(that.numerator) &&
                denominator.equals(that.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return "( " + numerator.toString() + " / " + denominator.toString() + " )";
    }

    @Override
    public int compareTo(SimpleFraction o) {
        return (this.numerator.multiply(o.denominator)).compareTo(o.numerator.multiply(this.denominator));
    }
}
