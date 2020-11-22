package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;
import com.alexrnv.algobook.various.poker.Rank;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * High Card: Highest value card.
 */
public class HighCard extends Combination {

    private static final int RATING = 1;

    private final Hand hand;
    private final Card card;
    private final Rank rank;

    protected HighCard(Hand hand, Card card, Rank rank) {
        this.hand = hand;
        this.card = card;
        this.rank = rank;
    }

    public Card getCard() {
        return card;
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
        return this.rank.compareTo(((HighCard) o).rank);
    }

    @Override
    protected List<Card> cardsInvolved() {
        return Collections.singletonList(card);
    }

    static final class Factory extends CombinationFactory<HighCard> {

        @Override
        int getCombinationPriority() {
            return RATING;
        }

        @Override
        protected boolean evaluateFullHand(Hand hand) {
            Map<Rank, Long> ranks = groupByRank(hand);
            return ranks.size() == 5;
        }

        @Override
        protected HighCard createFromFullHand(Hand hand) {
            Card highest = getHighest(hand);
            return new HighCard(hand, highest, highest.getRank());
        }
    }
}
