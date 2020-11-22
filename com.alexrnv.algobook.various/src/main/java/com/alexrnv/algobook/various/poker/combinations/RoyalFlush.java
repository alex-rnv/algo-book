package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;
import com.alexrnv.algobook.various.poker.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
 */
public class RoyalFlush extends Combination {

    private static final int RATING = 10;

    private final Hand hand;
    private final List<Card> cards;

    protected RoyalFlush(Hand hand, List<Card> cards) {
        this.hand = hand;
        this.cards = cards;
    }

    @Override
    protected int getRating() {
        return RATING;
    }

    @Override
    protected int compareWithSameRating(Combination o) {
        return 0;
    }

    @Override
    protected List<Card> cardsInvolved() {
        return Collections.unmodifiableList(cards);
    }


    static final class Factory extends CombinationFactory<RoyalFlush> {

        @Override
        int getCombinationPriority() {
            return RATING;
        }

        @Override
        protected boolean evaluateFullHand(Hand hand) {
            return isAllSameSuit(hand) && isAllConsecutive(hand) && getLowest(hand).getRank() == Rank.TEN;
        }

        @Override
        protected RoyalFlush createFromFullHand(Hand hand) {
            return new RoyalFlush(hand, new ArrayList<>(hand.getCards()));
        }
    }
}
