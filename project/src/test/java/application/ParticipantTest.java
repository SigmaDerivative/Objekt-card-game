package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Deck;
import application.Player;

public class ParticipantTest{
	private Player player;
	private Deck deck;
	
	@BeforeEach
	public void setUp() throws Exception {
		player = new Player();
		deck = new Deck();
	}
	
	@Test
	public void testDrawFromEmptyDeck() {
		try {
			for(int i = 0; i < 52; i++) {
				deck.draw();
			}
			player.drawCard(deck);
			fail("Exception was expected for drawing from empty deck");
		}
		catch (IllegalStateException e) {}
	}
	
	@Test
	public void testDrawRightCard() {
		Card card = new Card('S',1);
		ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card));
		player.drawCard(deck);
		assertEquals(cards.toString(), player.getCards().toString());
	}
	
	@Test
	public void testHandValueOneAce() {
		player.drawCard(deck);
		assertEquals(11, player.getValueOfHand());
	}
	
	@Test
	public void testZeroValue() {
		assertEquals(0, player.getValueOfHand());
	}
	
	@Test
	public void testTwoCards() {
		player.drawCard(deck);
		player.drawCard(deck);
		//ace + 2
		assertEquals(13, player.getValueOfHand());
	}
	
	@Test
	public void testTwoAces() {
		Card card = new Card('S',1);
		player.addCard(card);
		player.addCard(card);
		assertEquals(12, player.getValueOfHand());
	}
	@Test
	public void testJack() {
		Card card = new Card('S',11);
		player.addCard(card);
		assertEquals(10, player.getValueOfHand());
	}
	@Test
	public void testQueen() {
		Card card = new Card('S',12);
		player.addCard(card);
		assertEquals(10, player.getValueOfHand());
	}
	@Test
	public void testKing() {
		Card card = new Card('S',13);
		player.addCard(card);
		assertEquals(10, player.getValueOfHand());
	}
	@Test
	public void testThreeCards() {
		Card card = new Card('S',2);
		player.addCard(card);
		player.addCard(card);
		player.addCard(card);
		assertEquals(6, player.getValueOfHand());
	}
	@Test
	public void testThreeAces() {
		Card card = new Card('S',1);
		player.addCard(card);
		player.addCard(card);
		player.addCard(card);
		assertEquals(13, player.getValueOfHand());
	}
	@Test
	public void testFourAces() {
		Card card = new Card('S',1);
		player.addCard(card);
		player.addCard(card);
		player.addCard(card);
		player.addCard(card);
		assertEquals(14, player.getValueOfHand());
	}
	@Test
	public void testLowValueOneAce() {
		Card card1 = new Card('S',9);
		Card card2 = new Card('H',1);
		player.addCard(card1);
		player.addCard(card1);
		player.addCard(card2);
		assertEquals(19, player.getValueOfHand());
	}
}
