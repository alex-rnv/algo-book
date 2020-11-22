package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;
import com.alexrnv.algobook.various.poker.Rank;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class CombinationFactory<T extends Combination> {

    abstract int getCombinationPriority();

    public boolean evaluate(Hand hand) {
        if (!hand.isFull())
            throw new RuntimeException("The hand is not full for evaluation");

        return evaluateFullHand(hand);
    }

    abstract protected boolean evaluateFullHand(Hand hand);

    public T create(Hand hand) {
        if (!hand.isFull())
            throw new RuntimeException("The hand is not full for evaluation");
        return createFromFullHand(hand);
    }

    public T createOrNull(Hand hand) {
        if (!hand.isFull())
            throw new RuntimeException("The hand is not full for evaluation");

        if (!evaluate(hand))
            return null;

        return createFromFullHand(hand);
    }

    abstract protected T createFromFullHand(Hand hand);

    protected boolean isAllSameSuit(Hand hand) {
        assert hand.isFull();
        return hand.getCards().stream().map(Card::getSuit).distinct().count() == 1;
    }

    protected Card getLowest(Hand hand) {
        assert hand.isFull();
        return hand.getCards().first();
    }

    protected Card getHighest(Hand hand) {
        assert hand.isFull();
        return hand.getCards().last();
    }

    protected Map<Rank, Long> groupByRank(Hand hand) {
        assert hand.isFull();
        return hand.getCards().stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
    }

    protected List<Rank> getRankOfN(Hand hand, int n) {
        return groupByRank(hand)
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    protected List<Card> getCardsByRank(Hand hand, Rank rank) {
        return hand.getCards()
                .stream()
                .filter(card -> card.getRank() == rank)
                .collect(Collectors.toList());
    }

    protected boolean isAllConsecutive(Hand hand) {
        assert hand.isFull();
        Iterator<Card> iterator = hand.getCards().iterator();
        Rank previousRank = iterator.next().getRank();
        while (iterator.hasNext()) {
            Rank rank = iterator.next().getRank();
            if (rank.isNextFrom(previousRank)) {
                previousRank = rank;
            } else
                return false;
        }
        return true;
    }
}
