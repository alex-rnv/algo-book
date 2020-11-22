package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;
import com.alexrnv.algobook.various.poker.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *  Three of a kind and a pair.
 */
public class FullHouse extends Combination {

    final static int RATING = 7;

    private final Hand hand;
    private final List<Card> cards;
    private final ThreeOfAKind threeOfAKind;
    private final OnePair onePair;

    protected FullHouse(Hand hand, List<Card> cards, ThreeOfAKind threeOfAKind, OnePair onePair) {
        this.hand = hand;
        this.cards = cards;
        this.threeOfAKind = threeOfAKind;
        this.onePair = onePair;
    }

    @Override
    protected int getRating() {
        return RATING;
    }

    @Override
    protected int compareWithSameRating(Combination o) {
        int compareThree = this.threeOfAKind.compareTo(((FullHouse) o).threeOfAKind);
        if (compareThree == 0) {
            return this.onePair.compareTo(((FullHouse) o).onePair);
        } else
            return compareThree;
    }

    @Override
    protected List<Card> cardsInvolved() {
        return Collections.unmodifiableList(cards);
    }

    static final class Factory extends CombinationFactory<FullHouse> {

        @Override
        int getCombinationPriority() {
            return RATING;
        }

        @Override
        protected boolean evaluateFullHand(Hand hand) {
            Map<Rank, Long> ranks = groupByRank(hand);
            return ranks.size() == 2 && ranks.containsValue(3l) && ranks.containsValue(2l);        }

        @Override
        protected FullHouse createFromFullHand(Hand hand) {
            Rank rankThree = getRankOfN(hand, 3).get(0);
            List<Card> threeCards = getCardsByRank(hand, rankThree);
            ThreeOfAKind threeOfAKind = new ThreeOfAKind(hand, threeCards, rankThree);

            Rank rankPair = getRankOfN(hand, 2).get(0);
            List<Card> pair = getCardsByRank(hand, rankPair);
            OnePair onePair = new OnePair(hand, pair, rankPair);

            List<Card> cards = new ArrayList<>(threeCards);
            cards.addAll(pair);

            return new FullHouse(hand, cards, threeOfAKind, onePair);
        }
    }
}
