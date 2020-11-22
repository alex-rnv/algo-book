package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;
import com.alexrnv.algobook.various.poker.Rank;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * One Pair: Two cards of the same value.
 */
public class OnePair extends Combination {

    private static final int RATING = 2;

    private final Hand hand;
    private final List<Card> cards;
    private final Rank rank;

    protected OnePair(Hand hand, List<Card> cards, Rank rank) {
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
        return this.rank.compareTo(((OnePair) o).rank);
    }

    @Override
    protected List<Card> cardsInvolved() {
        return Collections.unmodifiableList(cards);
    }

    static final class Factory extends CombinationFactory<OnePair> {

        @Override
        int getCombinationPriority() {
            return RATING;
        }

        @Override
        protected boolean evaluateFullHand(Hand hand) {
            Map<Rank, Long> ranks = groupByRank(hand);
            return ranks.size() == 4 && ranks.containsValue(2l) && ranks.containsValue(1l);        }

        @Override
        protected OnePair createFromFullHand(Hand hand) {
            Rank rank = groupByRank(hand)
                    .entrySet()
                    .stream()
                    .filter(e -> e.getValue() == 2)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .get();
            List<Card> cards = hand.getCards()
                    .stream()
                    .filter(card -> card.getRank() == rank)
                    .collect(Collectors.toList());
            return new OnePair(hand, cards, rank);
        }
    }
}
