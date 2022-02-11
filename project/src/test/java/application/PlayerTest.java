package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Player;
import application.Deck;
import application.Card;

public class PlayerTest {
	private Player player;
	private Deck deck;
	
	@BeforeEach
	public void setUp() throws Exception {
		player = new Player();
		deck = new Deck();
		deck.emptyDeck();
	}
	
	@Test
	public void testDrawOneCardGetOneCard() {
		Card card = new Card('S',2);
		deck.addCard(card);
		player.hit(deck);
		assertEquals(1,player.getCards().size());
	}
	@Test
	public void testDrawOneCardClassCard() {
		Card card = new Card('S',2);
		deck.addCard(card);
		player.hit(deck);
		assertTrue(player.getCards().get(0) instanceof Card);
	}
	@Test
	public void testDrawOneCardGetRightCard() {
		//sjekker at man trekker første kortet i en ny bunke
		deck = new Deck();
		player.hit(deck);
		assertEquals("S1",player.getCards().get(0).toString());
	}
	@Test
	public void testDrawOneCardDeckEmpty() {
		Card card = new Card('S',2);
		deck.addCard(card);
		player.hit(deck);
		assertTrue(deck.isEmpty());
	}
	@Test
	public void testDrawOnTwentyOne() {
		Card card = new Card('S',7);
		for(int i = 0; i < 4; i++) {
			deck.addCard(card);
			player.hit(deck);
		}
		assertEquals(3,player.getCards().size());
	}
	@Test
	public void testDrawOnCharlie() {
		Card card = new Card('S',4);
		for(int i = 0; i < 6; i++) {
			deck.addCard(card);
			player.hit(deck);
		}
		assertEquals(5,player.getCards().size());
	}
}
