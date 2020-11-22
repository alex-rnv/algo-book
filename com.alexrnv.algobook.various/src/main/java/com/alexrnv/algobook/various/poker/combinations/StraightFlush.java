package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;
import com.alexrnv.algobook.various.poker.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Straight Flush: All cards are consecutive values of same suit.
 */
public class StraightFlush extends Combination {

    private static final int RATING = 9;

    private final Hand hand;
    private final List<Card> cards;
    protected final Rank highest;

    protected StraightFlush(Hand hand, List<Card> cards, Rank highest) {
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
        return highest.compareTo(((StraightFlush) o).highest);
    }

    @Override
    protected List<Card> cardsInvolved() {
        return Collections.unmodifiableList(cards);
    }


    static final class Factory extends CombinationFactory<StraightFlush> {

        @Override
        int getCombinationPriority() {
            return RATING;
        }

        @Override
        protected boolean evaluateFullHand(Hand hand) {
            return isAllSameSuit(hand) && isAllConsecutive(hand) && getHighest(hand).getRank() != Rank.ACE;
        }

        @Override
        protected StraightFlush createFromFullHand(Hand hand) {
            return new StraightFlush(hand, new ArrayList<>(hand.getCards()), getHighest(hand).getRank());

        }
    }
}
