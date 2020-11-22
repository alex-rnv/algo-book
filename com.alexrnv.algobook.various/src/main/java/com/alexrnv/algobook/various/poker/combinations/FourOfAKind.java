package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;
import com.alexrnv.algobook.various.poker.Rank;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Four cards of the same value.
 */
public class FourOfAKind extends Combination {

    private static final int RATING = 8;

    private final Hand hand;
    private final List<Card> cards;
    private final Rank rank;

    protected FourOfAKind(Hand hand, List<Card> cards, Rank rank) {
        this.hand = hand;
        this.cards = cards;
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    protected int getRating() {
        return RATING;
    }

    @Override
    protected int compareWithSameRating(Combination o) {
        return rank.compareTo(((FourOfAKind) o).rank);
    }

    @Override
    protected List<Card> cardsInvolved() {
        return Collections.unmodifiableList(cards);
    }


    static final class Factory extends CombinationFactory<FourOfAKind> {

        @Override
        int getCombinationPriority() {
            return RATING;
        }

        @Override
        protected boolean evaluateFullHand(Hand hand) {
            Map<Rank, Long> ranks = groupByRank(hand);
            return ranks.size() == 2 && ranks.containsValue(4l) && ranks.containsValue(1l);
        }

        @Override
        protected FourOfAKind createFromFullHand(Hand hand) {
            Rank rank = getRankOfN(hand, 4).get(0);
            List<Card> cards = getCardsByRank(hand, rank);
            return new FourOfAKind(hand, cards, rank);
        }
    }
}
