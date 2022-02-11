package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Deck;
import application.Card;

public class DeckTest {
	private Deck deck;
	
	@BeforeEach
	public void setUp() throws Exception {
		deck = new Deck();
	}
	
	@Test
	public void testNumberOfCards() {
		assertEquals(52, deck.getCards().size());
	}
	
	@Test
	public void testFirstCard() {
		//SHDC er rekkefølgen på suit
		//S1 aka spar ess er første kort i kortstokken
		assertEquals("S1", deck.getCards().get(0).toString());
	}
	
	@Test
	public void testLastCard() {
		//C13 aka kløver konge er siste
		assertEquals("C13", deck.getCards().get(51).toString());
	}
	
	@Test
	public void testSecondHearts() {
		//C13 aka kløver konge er siste
		assertEquals("H2", deck.getCards().get(14).toString());
	}
	
	@Test
	public void testEleventhDiamonds() {
		//C13 aka kløver konge er siste
		assertEquals("D11", deck.getCards().get(36).toString());
	}
	
	@Test
	public void testRemoveOneCard() {
		deck.draw();
		assertEquals(51,deck.getCards().size());
	}
	
	@Test
	public void testRemoveTenCards() {
		for(int i = 0; i < 10; i++) {
			deck.draw();
		}
		assertEquals(42,deck.getCards().size());
	}
	
	@Test
	public void testRemoveAllCards() {
		for(int i = 0; i < 52; i++) {
			deck.draw();
		}
		assertEquals(0,deck.getCards().size());
	}
	
	@Test
	public void testDrewCardClass() {
		assertTrue(deck.draw() instanceof Card);
	}
	
	@Test
	public void testRemovedFirstCard() {
		Card card = deck.draw();
		assertEquals("S1", card.toString());
	}
	
	@Test
	public void testIsEmpty() {
		for(int i = 0; i < 52; i++) {
			deck.draw();
		}
		assertTrue(deck.isEmpty());
	}
	
	@Test
	public void testIsEmptyTestMethod() {
		deck.emptyDeck();
		assertTrue(deck.isEmpty());
	}
	
	@Test
	public void testIsNotEmpty() {
		assertFalse(deck.isEmpty());
	}
	
	@Test
	public void testNotDrawableAtEmpty() {
		try {
			deck.emptyDeck();
			deck.draw();
			fail("Can't draw cards at 0 cards");
		} catch (IllegalStateException e) {
			
		}
		
	}
}
