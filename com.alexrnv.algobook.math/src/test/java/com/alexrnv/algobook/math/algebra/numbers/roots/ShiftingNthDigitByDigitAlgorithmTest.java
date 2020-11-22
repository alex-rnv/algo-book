package com.alexrnv.algobook.math.algebra.numbers.roots;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ShiftingNthDigitByDigitAlgorithmTest {

    @Test
    public void testSquareRootsBase10() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(1)}, 2);
        assertEquals("[1, 0000000000]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(2)}, 2);
        assertEquals("[1, 4142135623]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(3)}, 2);
        assertEquals("[1, 7320508075]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(4)}, 2);
        assertEquals("[2, 0000000000]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(5)}, 2);
        assertEquals("[2, 2360679774]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(1055)}, 2);
        assertEquals("[32, 4807635378]", Arrays.toString(result));
    }

    @Test
    public void testSquareRootBase10HighPrecision() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 200, new BigInteger[]{BigInteger.valueOf(2)}, 2);
        assertEquals("[1, 414213562373095048801688724209698078569671875376948073176679737990732478462" +
                "1070388503875343276415727350138462309122970249248360558507372126441214970999358314132226659275" +
                "0559275579995050115278206057147]", Arrays.toString(result));
    }

    @Test
    public void testSquareRootBase10BigValue() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(Long.MAX_VALUE)}, 2);
        assertEquals("[3037000499, 97604969228675240303]", Arrays.toString(result));
    }

    @Test
    public void testSquareRootBase10OfDecimal() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(1)}, 2);
        assertEquals("[1, 44913767461894385737]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(2)}, 2);
        assertEquals("[1, 48323969741913258974]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(3)}, 2);
        assertEquals("[1, 51657508881031011085]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(4)}, 2);
        assertEquals("[1, 54919333848296675407]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(5)}, 2);
        assertEquals("[1, 58113883008418966599]", Arrays.toString(result));
    }

    @Test
    public void testSquareRootBase10OfLongDecimal() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(212), BigInteger.valueOf(123456789)}, 2);
        assertEquals("[14, 56445868506619095212]", Arrays.toString(result));
    }

    @Test
    public void testCubeRootsBase10() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(1)}, 3);
        assertEquals("[1, 0000000000]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(2)}, 3);
        assertEquals("[1, 2599210498]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(3)}, 3);
        assertEquals("[1, 4422495703]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(4)}, 3);
        assertEquals("[1, 5874010519]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(5)}, 3);
        assertEquals("[1, 7099759466]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(8)}, 3);
        assertEquals("[2, 0000000000]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 10, new BigInteger[]{BigInteger.valueOf(1055)}, 3);
        assertEquals("[10, 1800713028]", Arrays.toString(result));
    }

    @Test
    public void testCubeRootBase10HighPrecision() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 200, new BigInteger[]{BigInteger.valueOf(7)}, 3);
        assertEquals("[1, 912931182772389101199116839548760282862439050345875766210647640447234276179230756" +
                "0075254414772857099045419139587907592279446152938642120131474866957124456140398881696814713797026267" +
                "4544661204406114776]", Arrays.toString(result));
    }

    @Test
    public void testCubeRootBase10BigValue() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(Long.MAX_VALUE)}, 3);
        assertEquals("[2097151, 99999999999992420877]", Arrays.toString(result));
    }

    @Test
    public void testCubeRootBase10OfDecimal() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(1)}, 3);
        assertEquals("[1, 28057916498749425524]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(2)}, 3);
        assertEquals("[1, 30059144685138699881]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(3)}, 3);
        assertEquals("[1, 32000612179591239769]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(4)}, 3);
        assertEquals("[1, 33886590016433904376]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(5)}, 3);
        assertEquals("[1, 35720880829745328575]", Arrays.toString(result));
    }

    @Test
    public void testCubeRootBase10OfLongDecimal() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(212), BigInteger.valueOf(123456789)}, 3);
        assertEquals("[5, 96388918556133794716]", Arrays.toString(result));
    }


    @Test
    public void testRootOfHighDegreeBase10() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(100)}, 1000);
        assertEquals("[1, 00461579027839514240]", Arrays.toString(result));

        result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(2)}, 1000);
        assertEquals("[1, 00069338746258063253]", Arrays.toString(result));
    }

    @Test
    public void testFourthRootOf7Base10() {
        String[] result = ShiftingNthDigitByDigitAlgorithm.solve( 20, new BigInteger[]{BigInteger.valueOf(7), BigInteger.ZERO}, 4);
        assertEquals("[1, 62657656169778574321]", Arrays.toString(result));
    }

}