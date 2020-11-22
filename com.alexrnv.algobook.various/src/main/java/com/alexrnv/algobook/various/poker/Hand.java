package com.alexrnv.algobook.various.poker;

import com.alexrnv.algobook.various.poker.combinations.Combination;
import com.alexrnv.algobook.various.poker.combinations.Evaluation;

import java.util.*;

public class Hand implements Comparable<Hand> {

    private static final int HAND_SIZE = 5;

    protected final SortedSet<Card> cards = new TreeSet<>((c1, c2) -> {
        int rank = c1.getRank().compareTo(c2.getRank());
        if (rank != 0) return rank;
        return 1;
    });

    protected final SortedSet<Combination> combinations = new TreeSet<>(Comparator.reverseOrder());

    public void deal(Collection<Card> cards) {
        for (Card card : cards) {
            deal(card);
        }
    }

    public void deal(Card card) {
        if (cards.size() == HAND_SIZE)
            throw new RuntimeException("The hand is already full, can't deal more");

        this.cards.add(card);

        if (cards.size() == HAND_SIZE)
            eval();
    }

    public SortedSet<Card> getCards() {
        return Collections.unmodifiableSortedSet(cards);
    }

    public boolean isFull() {
        return cards.size() == HAND_SIZE;
    }

    private void eval() {
        this.combinations.addAll(Evaluation.evaluate(this));
    }

    @Override
    public int compareTo(Hand o) {
        assert isFull() && o.isFull();
        Iterator<Combination> iteratorThis = combinations.iterator();
        Iterator<Combination> iteratorThat = o.combinations.iterator();
        while (iteratorThis.hasNext() && iteratorThat.hasNext()) {
            int compare = iteratorThis.next().compareTo(iteratorThat.next());
            if (compare != 0) return compare;
        }
        throw new RuntimeException("Illegal combinations state");
    }
}
