package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;

import java.util.List;

public abstract class Combination implements Comparable<Combination> {

    @Override
    public final int compareTo(Combination o) {
        int rating = getRating() - o.getRating();
        if (rating == 0) {
            return compareWithSameRating(o);
        } else
            return rating;
    }

    protected abstract int getRating();
    protected abstract int compareWithSameRating(Combination o);

    protected abstract List<Card> cardsInvolved();
}
