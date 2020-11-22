package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;
import com.alexrnv.algobook.various.poker.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Straight: All cards are consecutive values.
 */
public class Straight extends Combination {

    private static final int RATING = 5;

    private final Hand hand;
    private final List<Card> cards;
    private final Rank highest;

    protected Straight(Hand hand, List<Card> cards, Rank highest) {
        this.hand = hand;
        this.cards = cards;
        this.highest = highest;
    }

    public Rank getHighest() {
        return highest;
    }

    @Override
    protected int getRating() {
        return RATING;
    }

    @Override
    protected int compareWithSameRating(Combination o) {
        return this.highest.compareTo(((Straight) o).highest);
    }

    @Override
    protected List<Card> cardsInvolved() {
        return Collections.unmodifiableList(cards);
    }


    static final class Factory extends CombinationFactory<Straight> {

        @Override
        int getCombinationPriority() {
            return RATING;
        }

        @Override
        protected boolean evaluateFullHand(Hand hand) {
            return isAllConsecutive(hand);
        }

        @Override
        protected Straight createFromFullHand(Hand hand) {
            return new Straight(hand, new ArrayList<>(hand.getCards()), getHighest(hand).getRank());
        }
    }
}
