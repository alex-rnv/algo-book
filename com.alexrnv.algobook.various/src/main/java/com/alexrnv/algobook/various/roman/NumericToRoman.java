package com.alexrnv.algobook.various.roman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NumericToRoman {

    private static final List<Denomination> DECIMAL_DENOMINATIONS = new ArrayList<>() {{
        add(Denomination.M);
        add(Denomination.C);
        add(Denomination.X);
        add(Denomination.I);
    }};

    private static final Map<Denomination, Denomination> NEXT_FIVEFOLD_MULTIPLE = new HashMap<>() {{
        put(Denomination.I, Denomination.V);
        put(Denomination.X, Denomination.L);
        put(Denomination.C, Denomination.D);
    }};

    private static final Map<Denomination, Denomination> NEXT_TENFOLD_MULTIPLE = new HashMap<>() {{
        put(Denomination.I, Denomination.X);
        put(Denomination.X, Denomination.C);
        put(Denomination.C, Denomination.M);
    }};

    private int remainder;
    private final StringBuilder romanNumeral = new StringBuilder();

    String convert(int numeric) {
        remainder = numeric;
        for (Denomination denomination : DECIMAL_DENOMINATIONS) {
            int valueInDenomination = remainder / denomination.number;
            appendNumber(valueInDenomination, denomination);
            remainder -= valueInDenomination * denomination.number;
        }

        return romanNumeral.toString();
    }

    private void appendNumber(int valueInDenomination, Denomination denomination) {
        if (valueInDenomination == 0) {
        } else if (valueInDenomination < 4) {
            appendMultiple(denomination, valueInDenomination);
        } else if (valueInDenomination == 4) {
            Denomination five = NEXT_FIVEFOLD_MULTIPLE.get(denomination);
            if (five == null) {
                appendMultiple(denomination, valueInDenomination);
            } else {
                appendOnce(denomination);
                appendOnce(five);
            }
        } else if (valueInDenomination < 9) {
            Denomination five = NEXT_FIVEFOLD_MULTIPLE.get(denomination);
            if (five == null) {
                appendMultiple(denomination, valueInDenomination);
            } else {
                appendOnce(five);
                appendMultiple(denomination, valueInDenomination - 5);
            }
        } else if (valueInDenomination == 9) {
            Denomination ten = NEXT_TENFOLD_MULTIPLE.get(denomination);
            if (ten == null) {
                appendMultiple(denomination, valueInDenomination);
            } else {
                appendOnce(denomination);
                appendOnce(ten);
            }
        } else /* M is allowed 10+ times*/{
            appendMultiple(denomination, valueInDenomination);
        }
    }

    private void appendOnce(Denomination denomination) {
        romanNumeral.append(denomination.value);
    }

    private void appendMultiple(Denomination denomination, int times) {
        while(times-- >0)
            romanNumeral.append(denomination.value);
    }

}
