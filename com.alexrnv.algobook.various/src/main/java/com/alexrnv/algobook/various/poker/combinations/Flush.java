package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;
import com.alexrnv.algobook.various.poker.Rank;
import com.alexrnv.algobook.various.poker.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * All cards of the same suit.
 */
public class Flush extends Combination {

    private static final int RATING = 6;

    private final Hand hand;
    private final List<Card> cards;
    private final Rank highest;
    private final Suit suit;

    protected Flush(Hand hand, List<Card> cards, Rank highest, Suit suit) {
        this.hand = hand;
        this.cards = cards;
        this.highest = highest;
        this.suit = suit;
    }

    public Rank getHighest() {
        return highest;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    protected int getRating() {
        return RATING;
    }

    @Override
    protected int compareWithSameRating(Combination o) {
        return this.highest.compareTo(((Flush) o).highest);
    }

    @Override
    protected List<Card> cardsInvolved() {
        return Collections.unmodifiableList(cards);
    }

    static final class Factory extends CombinationFactory<Flush> {

        @Override
        int getCombinationPriority() {
            return RATING;
        }

        @Override
        protected boolean evaluateFullHand(Hand hand) {
            return isAllSameSuit(hand);
        }

        @Override
        protected Flush createFromFullHand(Hand hand) {
            Card highest = getHighest(hand);
            return new Flush(hand, new ArrayList<>(hand.getCards()), highest.getRank(), highest.getSuit());
        }
    }
}
