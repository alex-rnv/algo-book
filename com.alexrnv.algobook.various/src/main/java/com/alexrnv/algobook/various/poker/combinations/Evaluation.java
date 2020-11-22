package com.alexrnv.algobook.various.poker.combinations;

import com.alexrnv.algobook.various.poker.Card;
import com.alexrnv.algobook.various.poker.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Evaluation {

    private static final List<CombinationFactory<?>> evaluationOrder;

    static {
        List<CombinationFactory<?>> order  = new ArrayList<>();
        order.add(new RoyalFlush.Factory());
        order.add(new StraightFlush.Factory());
        order.add(new FourOfAKind.Factory());
        order.add(new FullHouse.Factory());
        order.add(new Flush.Factory());
        order.add(new Straight.Factory());
        order.add(new ThreeOfAKind.Factory());
        order.add(new TwoPairs.Factory());
        order.add(new OnePair.Factory());
        order.add(new HighCard.Factory());
        Collections.sort(order, (f1, f2) -> f2.getCombinationPriority() - f1.getCombinationPriority());
        evaluationOrder = Collections.unmodifiableList(order);
    }



    public static List<Combination> evaluate(Hand hand) {
        List<Combination> result = new ArrayList<>();

        for (CombinationFactory evaluator : evaluationOrder) {
            if (evaluator.evaluate(hand)) {
                Combination combination = evaluator.create(hand);
                result.add(combination);
                List<Card> usedCards = combination.cardsInvolved();
                List<Card> cardsLeft = new ArrayList<>(hand.getCards());
                cardsLeft.removeAll(usedCards);
                //only high cards may be left
                for (Card card : cardsLeft) {
                    result.add(new HighCard(hand, card, card.getRank()));
                }
                break;
            }
        }
        return result;
    }
}
