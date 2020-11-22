package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;
import com.alexrnv.algobook.various.poker.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Two Pairs: Two different pairs.
 */
public class TwoPairs extends Combination {

    private static final int RATING = 3;

    private final Hand hand;
    private final List<Card> cards;
    private final OnePair pairHigh;
    private final OnePair pairLow;

    protected TwoPairs(Hand hand, List<Card> cards, OnePair pairOne, OnePair pairTwo) {
        this.hand = hand;
        this.cards = cards;
        if (pairOne.compareTo(pairTwo) > 0) {
            this.pairHigh = pairOne;
            this.pairLow = pairTwo;
        } else {
            this.pairHigh = pairTwo;
            this.pairLow = pairOne;
        }
    }

    public OnePair getPairHigh() {
        return pairHigh;
    }

    public OnePair getPairLow() {
        return pairLow;
    }

    @Override
    protected int getRating() {
        return RATING;
    }

    @Override
    protected int compareWithSameRating(Combination o) {
        int compareHighPair = this.pairHigh.compareTo(((TwoPairs) o).pairHigh);
        if (compareHighPair == 0) {
            return this.pairLow.compareTo(((TwoPairs) o).pairLow);
        } else
            return compareHighPair;
    }

    @Override
    protected List<Card> cardsInvolved() {
        return Collections.unmodifiableList(cards);
    }

    static final class Factory extends CombinationFactory<TwoPairs> {

        @Override
        int getCombinationPriority() {
            return RATING;
        }

        @Override
        protected boolean evaluateFullHand(Hand hand) {
            Map<Rank, Long> ranks = groupByRank(hand);
            return ranks.size() == 3 && ranks.containsValue(2l) && ranks.containsValue(1l);        }

        @Override
        protected TwoPairs createFromFullHand(Hand hand) {
            List<Rank> pairRanks = getRankOfN(hand, 2);
            List<Card> cards0 = getCardsByRank(hand, pairRanks.get(0));
            OnePair pairOne = new OnePair(hand, cards0, pairRanks.get(0));
            List<Card> cards1 = getCardsByRank(hand, pairRanks.get(1));
            OnePair pairTwo = new OnePair(hand, cards1, pairRanks.get(1));
            List<Card> cards = new ArrayList<>(cards0);
            cards.addAll(cards1);

            return new TwoPairs(hand, cards, pairOne, pairTwo);
        }
    }
}
