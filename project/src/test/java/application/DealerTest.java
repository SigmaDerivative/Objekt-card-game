package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Dealer;
import application.Deck;
import application.Card;

public class DealerTest {
	private Dealer dealer;
	private Deck deck;
	
	@BeforeEach
	public void setUp() throws Exception {
		dealer = new Dealer();
		deck = new Deck();
		deck.emptyDeck();
	}
	@Test
	public void testHiddenCardOutsideOfGetterAndSetter() {
		Card hCard = new Card('S',5);
		Card card = new Card('H',9);
		deck.addCard(hCard);
		deck.addCard(card);
		deck.addCard(card);
		dealer.setHiddenCard(deck);
		dealer.dealerTurn(deck);
		assertEquals(23,dealer.getValueOfHand());
	}
	@Test
	public void testEmptyingDeck() {
		Card card = new Card('S',7);
		deck.addCard(card);
		deck.addCard(card);
		deck.addCard(card);
		dealer.setHiddenCard(deck);
		dealer.dealerTurn(deck);
		assertTrue(deck.isEmpty());
	}
	@Test
	public void testDrawOn14() {
		Card card = new Card('S',7);
		deck.addCard(card);
		deck.addCard(card);
		deck.addCard(card);
		dealer.setHiddenCard(deck);
		dealer.dealerTurn(deck);
		assertEquals(3,dealer.getCards().size());
	}
	@Test
	public void testDrawOn16() {
		Card card = new Card('S',8);
		deck.addCard(card);
		deck.addCard(card);
		deck.addCard(card);
		dealer.setHiddenCard(deck);
		dealer.dealerTurn(deck);
		assertEquals(3,dealer.getCards().size());
	}
	@Test
	public void testDrawOn17() {
		Card card1 = new Card('C',9);
		Card card2 = new Card('S',8);
		deck.addCard(card1);
		deck.addCard(card2);
		deck.addCard(card2);
		dealer.setHiddenCard(deck);
		dealer.dealerTurn(deck);
		assertEquals(2,dealer.getCards().size());
	}
	@Test
	public void testDrawOn21() {
		Card card1 = new Card('C',1);
		Card card2 = new Card('S',10);
		deck.addCard(card1);
		deck.addCard(card2);
		deck.addCard(card2);
		dealer.setHiddenCard(deck);
		dealer.dealerTurn(deck);
		assertEquals(2,dealer.getCards().size());
	}
	@Test
	public void testDrawOn22() {
		Card card1 = new Card('C',2);
		Card card2 = new Card('S',10);
		deck.addCard(card1);
		deck.addCard(card2);
		deck.addCard(card2);
		deck.addCard(card2);
		dealer.setHiddenCard(deck);
		dealer.dealerTurn(deck);
		assertEquals(3,dealer.getCards().size());
	}
}
