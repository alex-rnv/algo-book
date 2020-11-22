package com.alexrnv.algobook.various.poker;

import com.alexrnv.algobook.various.poker.combinations.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.alexrnv.algobook.various.poker.Rank.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HandTest {

    private final Random random = new Random();

    @Test
    public void testRoyalFlush() {
        for (Suit suit : Suit.values()) {
            Hand hand = new Hand();
            hand.deal(new Card(Rank.TEN, suit));
            hand.deal(new Card(Rank.JACK, suit));
            hand.deal(new Card(QUEEN, suit));
            hand.deal(new Card(Rank.KING, suit));
            hand.deal(new Card(ACE, suit));
            assertEquals(1, hand.combinations.size());
            Combination combination = hand.combinations.first();
            assertTrue(combination instanceof RoyalFlush);
        }
    }

    @Test
    public void testStraightFlush() {
        for (Rank rank = Rank.KING; rank != Rank.NINE; rank = rank.getPrevious()) {
            for (Suit suit : Suit.values()) {
                Hand hand = new Hand();
                hand.deal(new Card(rank, suit));
                hand.deal(new Card(rank.getPrevious(), suit));
                hand.deal(new Card(rank.getPrevious().getPrevious(), suit));
                hand.deal(new Card(rank.getPrevious().getPrevious().getPrevious(), suit));
                hand.deal(new Card(rank.getPrevious().getPrevious().getPrevious().getPrevious(), suit));
                assertEquals(1, hand.combinations.size());
                Combination combination = hand.combinations.first();
                assertTrue(combination instanceof StraightFlush);
                assertTrue(((StraightFlush) combination).getHighest() == rank);
            }
        }
    }

    @Test
    public void testFourOfAKind() {
        for (Rank rank = ACE; rank != null; rank = rank.getPrevious()) {
            Hand hand = new Hand();
            hand.deal(new Card(rank, Suit.SPADES));
            hand.deal(new Card(rank, Suit.CLUBS));
            hand.deal(new Card(rank, Suit.DIAMONDS));
            hand.deal(new Card(rank, Suit.HEARTS));
            hand.deal(new Card(randomRankExcept(rank), randomSuit()));
            assertEquals(2, hand.combinations.size());
            Combination four = hand.combinations.first();
            assertTrue(four instanceof FourOfAKind);
            assertTrue(((FourOfAKind) four).getRank() == rank);
            Combination one = hand.combinations.last();
            assertTrue(one instanceof HighCard);
        }
    }

    @Test
    public void testFullHouse() {
        List<Rank[]> rankPairs = generateRankPairs();

        for (Rank[] ranks : rankPairs) {
            Hand hand = new Hand();
            hand.deal(new Card(ranks[0], Suit.SPADES));
            hand.deal(new Card(ranks[0], Suit.DIAMONDS));
            hand.deal(new Card(ranks[0], Suit.HEARTS));
            hand.deal(new Card(ranks[1], Suit.SPADES));
            hand.deal(new Card(ranks[1], Suit.HEARTS));
            assertEquals(1, hand.combinations.size());
            Combination fullHouse = hand.combinations.first();
            assertTrue(fullHouse instanceof FullHouse);
        }
    }

    @Test
    public void testStraight() {
        for (Rank rank = ACE; rank != Rank.NINE; rank = rank.getPrevious()) {
            Hand hand = new Hand();
            hand.deal(new Card(rank, Suit.SPADES));
            hand.deal(new Card(rank.getPrevious(), Suit.DIAMONDS));
            hand.deal(new Card(rank.getPrevious().getPrevious(), Suit.SPADES));
            hand.deal(new Card(rank.getPrevious().getPrevious().getPrevious(), Suit.DIAMONDS));
            hand.deal(new Card(rank.getPrevious().getPrevious().getPrevious().getPrevious(), Suit.CLUBS));
            assertEquals(1, hand.combinations.size());
            Combination combination = hand.combinations.first();
            assertTrue(combination instanceof Straight);
            assertTrue(((Straight) combination).getHighest() == rank);
        }
    }

    @Test
    public void testFlush() {
        for (Suit suit : Suit.values()) {
            Hand hand = new Hand();
            hand.deal(new Card(ACE, suit));
            hand.deal(new Card(KING, suit));
            hand.deal(new Card(TEN, suit));
            hand.deal(new Card(SIX, suit));
            hand.deal(new Card(EIGHT, suit));
            assertEquals(1, hand.combinations.size());
            Combination combination = hand.combinations.first();
            assertTrue(combination instanceof Flush);
            assertTrue(((Flush) combination).getHighest() == ACE);
            assertTrue(((Flush) combination).getSuit() == suit);
        }
    }

    @Test
    public void testThreeOfAKind() {
        Hand hand = new Hand();
        hand.deal(new Card(ACE, Suit.CLUBS));
        hand.deal(new Card(KING, Suit.CLUBS));
        hand.deal(new Card(KING, Suit.DIAMONDS));
        hand.deal(new Card(KING, Suit.SPADES));
        hand.deal(new Card(NINE, Suit.CLUBS));
        assertEquals(3, hand.combinations.size());
        Combination three = hand.combinations.first();
        assertTrue(three instanceof ThreeOfAKind);
        assertTrue(((ThreeOfAKind) three).getRank() == KING);
        Combination highCard = hand.combinations.last();
        assertTrue(highCard instanceof HighCard);
        assertTrue(((HighCard) highCard).getRank() == NINE);
    }

    @Test
    public void testTwoPairs() {
        Hand hand = new Hand();
        hand.deal(new Card(ACE, Suit.CLUBS));
        hand.deal(new Card(KING, Suit.CLUBS));
        hand.deal(new Card(KING, Suit.DIAMONDS));
        hand.deal(new Card(NINE, Suit.SPADES));
        hand.deal(new Card(NINE, Suit.CLUBS));
        assertEquals(2, hand.combinations.size());
        Combination twoPairs = hand.combinations.first();
        assertTrue(twoPairs instanceof TwoPairs);
        assertTrue(((TwoPairs) twoPairs).getPairHigh().getRank() == KING);
        assertTrue(((TwoPairs) twoPairs).getPairLow().getRank() == NINE);
        Combination highCard = hand.combinations.last();
        assertTrue(highCard instanceof HighCard);
        assertTrue(((HighCard) highCard).getRank() == ACE);
    }

    @Test
    public void testOnePair() {
        Hand hand = new Hand();
        hand.deal(new Card(QUEEN, Suit.CLUBS));
        hand.deal(new Card(JACK, Suit.CLUBS));
        hand.deal(new Card(JACK, Suit.DIAMONDS));
        hand.deal(new Card(SIX, Suit.SPADES));
        hand.deal(new Card(NINE, Suit.CLUBS));
        assertEquals(4, hand.combinations.size());
        Combination twoPairs = hand.combinations.first();
        assertTrue(twoPairs instanceof OnePair);
        assertTrue(((OnePair) twoPairs).getRank() == JACK);
        Combination highCard = hand.combinations.last();
        assertTrue(highCard instanceof HighCard);
        assertTrue(((HighCard) highCard).getRank() == SIX);
    }

    @Test
    public void testHighCard() {
        Hand hand = new Hand();
        hand.deal(new Card(QUEEN, Suit.CLUBS));
        hand.deal(new Card(JACK, Suit.CLUBS));
        hand.deal(new Card(SEVEN, Suit.DIAMONDS));
        hand.deal(new Card(SIX, Suit.SPADES));
        hand.deal(new Card(NINE, Suit.CLUBS));
        assertEquals(5, hand.combinations.size());
        Combination high = hand.combinations.first();
        assertTrue(high instanceof HighCard);
        assertTrue(((HighCard) high).getRank() == QUEEN);
        Combination low = hand.combinations.last();
        assertTrue(low instanceof HighCard);
        assertTrue(((HighCard) low).getRank() == SIX);
    }

    @Test
    public void testPlayHands() {
        Hand hand1 = new Hand();
        hand1.deal(new Card(ACE, Suit.CLUBS));
        hand1.deal(new Card(ACE, Suit.DIAMONDS));
        hand1.deal(new Card(ACE, Suit.HEARTS));
        hand1.deal(new Card(Rank.TEN, Suit.HEARTS));
        hand1.deal(new Card(QUEEN, Suit.DIAMONDS));

        Hand hand2 = new Hand();
        hand2.deal(new Card(Rank.SIX, Suit.SPADES));
        hand2.deal(new Card(Rank.KING, Suit.DIAMONDS));
        hand2.deal(new Card(Rank.KING, Suit.HEARTS));
        hand2.deal(new Card(Rank.KING, Suit.CLUBS));
        hand2.deal(new Card(Rank.KING, Suit.SPADES));

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    private Rank randomRankExcept(Rank except) {
        Rank[] ranks = Rank.values();
        Rank rank = ranks[random.nextInt(ranks.length)];
        if (rank == except) {
            rank = rank.getPrevious();
            if (rank == null) {
                rank = ACE;
            }
        }
        return rank;
    }

    private Suit randomSuit() {
        Suit[] suits = Suit.values();
        return suits[random.nextInt(suits.length)];
    }

    private List<Rank[]> generateRankPairs() {
        List<Rank[]> rankPairs = new ArrayList<>();
        for (Rank rank1 : Rank.values()) {
            for (Rank rank2 : Rank.values()) {
                if (rank1 == rank2) continue;
                rankPairs.add(new Rank[]{rank1, rank2});
            }
        }
        return rankPairs;
    }

}