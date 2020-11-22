package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;
import com.alexrnv.algobook.various.poker.Rank;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Three of a Kind: Three cards of the same value.
 */
public class ThreeOfAKind extends Combination {

    private static final int RATING = 4;

    private final Hand hand;
    private final List<Card> cards;
    private final Rank rank;

    protected ThreeOfAKind(Hand hand, List<Card> cards, Rank rank) {
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
        return this.rank.compareTo(((ThreeOfAKind) o).rank);
    }

    @Override
    protected List<Card> cardsInvolved() {
        return Collections.unmodifiableList(cards);
    }


    static final class Factory extends CombinationFactory<ThreeOfAKind> {

        @Override
        int getCombinationPriority() {
            return RATING;
        }

        @Override
        protected boolean evaluateFullHand(Hand hand) {
            Map<Rank, Long> ranks = groupByRank(hand);
            return ranks.size() == 3 && ranks.containsValue(3l) && ranks.containsValue(1l);
        }

        @Override
        protected ThreeOfAKind createFromFullHand(Hand hand) {
            Rank rank = getRankOfN(hand, 3).get(0);
            List<Card> cards = getCardsByRank(hand, rank);
            return new ThreeOfAKind(hand, cards, rank);
        }
    }
}
