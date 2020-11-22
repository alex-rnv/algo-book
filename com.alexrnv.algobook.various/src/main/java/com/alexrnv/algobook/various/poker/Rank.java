package com.alexrnv.algobook.various.poker;

public enum Rank {
    TWO(null),
    THREE(TWO),
    FOUR(THREE),
    FIVE(FOUR),
    SIX(FIVE),
    SEVEN(SIX),
    EIGHT(SEVEN),
    NINE(EIGHT),
    TEN(NINE),
    JACK(TEN),
    QUEEN(JACK),
    KING(QUEEN),
    ACE(KING);

    private final Rank previous;

    Rank(Rank previous) {
        this.previous = previous;
    }

    public boolean isNextFrom(Rank previous) {
        return this.previous == previous;
    }

    protected Rank getPrevious() {
        return previous;
    }

}
