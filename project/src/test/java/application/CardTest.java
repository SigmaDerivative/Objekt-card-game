package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class CardTest {
	
	@Test
	public void testCardConstructorWrongSuit() {
		try {
			Card card = new Card('A', 1);
			fail("Exception was expected for non existing suit");
		}
		catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testCardConstructorLargeFace() {
		try {
			Card card = new Card('S', 14);
			fail("Exception was expected for non existing face");
		}
		catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testCardConstructorZeroFace() {
		try {
			Card card = new Card('S', 0);
			fail("Exception was expected for non existing face");
		}
		catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testCardConstructorNegativeFace() {
		try {
			Card card = new Card('S', -5);
			fail("Exception was expected for non existing face");
		}
		catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testCardConstructorWrongSuitAndFace() {
		try {
			Card card = new Card('B', 14);
			fail("Exception was expected for non existing suit and non existing face");
		}
		catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testCardToString() {
		Card card = new Card('D', 5);
		assertEquals("D5",card.toString());
	}
	
	@Test
	public void testCardSuits() {
		String suitsSolution = "SHDC";
		assertEquals(suitsSolution, Card.getSuits());
	}
}
