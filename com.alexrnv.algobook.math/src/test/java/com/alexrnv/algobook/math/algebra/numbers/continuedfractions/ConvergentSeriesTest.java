package com.alexrnv.algobook.math.algebra.numbers.continuedfractions;

import com.alexrnv.algobook.math.algebra.numbers.fractions.SimpleFraction;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

public class ConvergentSeriesTest {

    @Test
    public void test_E_Expansion() {
        GeneralizedContinuedFraction continuedFraction = new GeneralizedContinuedFraction(n -> BigInteger.ONE, n -> {
            if (n.equals(BigInteger.ONE)) return BigInteger.ZERO;
            BigInteger i = n.subtract(BigInteger.ONE);
            if (i.mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO)) {
                return i.multiply(BigInteger.TWO).divide(BigInteger.valueOf(3));
            }
            return BigInteger.ONE;
        });

        List<SimpleFraction> series = ConvergentSeries.generateConvergentSeries(continuedFraction, 100);
        Assert.assertEquals(SimpleFraction.create(1457L, 536L), series.get(9));
        Assert.assertEquals(SimpleFraction.create(new BigInteger("6963524437876961749120273824619538346438023188214475670667"), new BigInteger("2561737478789858711161539537921323010415623148113041714756")), series.get(99));
    }

    @Test
    public void test_Sqrt2_Expansion() {
        GeneralizedContinuedFraction continuedFraction = new GeneralizedContinuedFraction(
                n -> BigInteger.ONE, n -> n.compareTo(BigInteger.ONE) >= 0 ? BigInteger.TWO : BigInteger.ONE);

        List<SimpleFraction> series = ConvergentSeries.generateConvergentSeries(continuedFraction, 100);
        Assert.assertEquals(SimpleFraction.create(8119L, 5741L), series.get(9));
        Assert.assertEquals(SimpleFraction.create(new BigInteger("228725309250740208744750893347264645481"), new BigInteger("161733217200188571081311986634082331709")), series.get(99));
    }

}