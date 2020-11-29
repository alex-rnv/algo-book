package com.alexrnv.algobook.various.roman;

import java.util.HashMap;
import java.util.Map;

import static com.alexrnv.algobook.various.roman.Denomination.*;

class RomanToNumeric {

    private static final Map<Denomination, Integer> DENOMINATION_LIMITS = new HashMap<>() {{
        put(I, 10);
        put(V, 1);
        put(X, 10);
        put(L, 1);
        put(C, 10);
        put(D, 1);
        put(M, 10);
    }};

    private static final Map<Denomination, Denomination> ALLOWED_SUBTRACTIVE_NUMERALS = new HashMap<>() {{
        put(V, I);
        put(X, I);
        put(L, X);
        put(C, X);
        put(D, C);
        put(M, C);

    }};

    private final Map<Denomination, Integer> totalDenominationCounters = new HashMap<>();
    private int currentPosition;
    private int currentNumber;
    private Denomination previousDenomination;
    private int previousDenominationStringCounter;


    public int convert(String roman) {
        while (currentPosition < roman.length()) {
            char numeral = roman.charAt(currentPosition);
            Denomination currentDenomination = Denomination.fromRoman(numeral);
            if (currentDenomination == null) {
                throw new InvalidRomanNumeralException(String.format("Invalid numeral '%s'", numeral));
            }

            updateCounterAndCheckLimits(currentDenomination);

            if (orderIsDecreasing(currentDenomination)) {
                currentNumber += currentDenomination.number;
                previousDenominationStringCounter = 1;
            } else if (sameDenomination(currentDenomination)) {
                currentNumber += currentDenomination.number;
                previousDenominationStringCounter++;
            } else /* order is increasing*/{
                checkCorrectSubtractiveOrder(currentDenomination);
                //if everything is correct, we need to discard the previous addition of
                //subtractive denomination and take into account subtractive part itself
                currentNumber += (currentDenomination.number - 2 * previousDenomination.number);
                previousDenominationStringCounter = 1;

            }
            previousDenomination = currentDenomination;
            currentPosition++;
        }
        return currentNumber;
    }

    private void updateCounterAndCheckLimits(Denomination denomination) {
        int limit = DENOMINATION_LIMITS.get(denomination);
        int counter = totalDenominationCounters.merge(denomination, 1, Integer::sum);
        if (counter > limit)
            throw new InvalidRomanNumeralException(String.format("Number of digits for %s exceeded the limit of %d", denomination.value, limit));
    }

    private boolean orderIsDecreasing(Denomination currentDenomination) {
        return previousDenomination == null || currentDenomination.number < previousDenomination.number;
    }

    private boolean sameDenomination(Denomination currentDenomination) {
        return previousDenomination == currentDenomination;
    }

    private void checkCorrectSubtractiveOrder(Denomination currentDenomination) {
        Denomination allowed = ALLOWED_SUBTRACTIVE_NUMERALS.get(currentDenomination);
        if (allowed != previousDenomination) {
            throw new InvalidRomanNumeralException(String.format("Only '%s' is allowed as a subtractive part for '%s', got '%s'",
                    allowed.value, currentDenomination.value, previousDenomination.value));
        } else if (previousDenominationStringCounter > 1) {
            throw new InvalidRomanNumeralException(String.format("Only one '%s' numeral is allowed as a subtractive part for '%s', got %d",
                    allowed.value, currentDenomination.value, previousDenominationStringCounter));
        }
    }

}
