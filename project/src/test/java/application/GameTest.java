package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
	private Game game;
	
	@BeforeEach
	public void setUp() throws Exception {
		game = new Game();
		game.start();
		game.hardReset();
	}
	
	@Test
	public void testTwoStartCardsPlayer() {
		assertEquals(2,game.getPlayer().getCards().size());
	}
	
	@Test
	public void testUniqueStartCardsPlayer() {
		ArrayList<Card> cards = game.getPlayer().getCards();
		assertNotEquals(cards.get(0).toString(), cards.get(1).toString());
	}
	
	@Test
	public void testOneHiddenCardDealer() {
		assertTrue(game.getDealer().getHiddenCard() instanceof Card);
	}
	
	@Test
	public void testOneCardDealer() {
		assertEquals(1,game.getDealer().getCards().size());
	}
	
	@Test
	public void testUniqueStartCardsDealer() {
		assertNotEquals(game.getDealer().getCards().get(0), game.getDealer().getHiddenCard());
	}
	
	@Test
	public void test48CardsDeckAtStart() {
		assertEquals(48, game.getDeck().getCards().size());
	}
	
	@Test
	public void testUniqueStartCardsPlayerDealer() {
		assertNotEquals(game.getPlayer().getCards().get(0), game.getDealer().getCards().get(0));
	}
	@Test
	public void testUniqueStartCardsPlayerDealerHidden() {
		assertNotEquals(game.getPlayer().getCards().get(0), game.getDealer().getHiddenCard());
	}
	@Test
	public void testUniqueStartCardsPlayer2Dealer() {
		assertNotEquals(game.getPlayer().getCards().get(1), game.getDealer().getCards().get(0));
	}
	@Test
	public void testUniqueStartCardsPlayer2DealerHidden() {
		assertNotEquals(game.getPlayer().getCards().get(1), game.getDealer().getHiddenCard());
	}
	//antar at vi ikke trenger å teste at det ikke er overlapp på tvers av player, dealer og deck etter alle disse testene og deck testene
	
	@Test
	public void testHardReset() {
		ReadAndWrite.write("scores.txt", "5,5");
		game.hardReset();
		try {
			assertEquals("0,0",ReadAndWrite.read("scores.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Scores setup
	public void checkEvenScore() {
		game.endGame();
		try {
			assertEquals("0,0",ReadAndWrite.read("scores.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void checkPlayerWin() {
		game.endGame();
		try {
			assertEquals("1,0",ReadAndWrite.read("scores.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void checkDealerWin() {
		game.endGame();
		try {
			assertEquals("0,1",ReadAndWrite.read("scores.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//0 = player, 1 = dealer
	public void blackJack(int i) {
		Card card1 = new Card('D',1);
		Card card2 = new Card('H',10);
		
		if(i == 0) {
			game.getPlayer().addCard(card1);
			game.getPlayer().addCard(card2);
		} else {
			game.getDealer().addCard(card1);
			game.getDealer().addCard(card2);
		}
	}
	public void charlie(int i) {
		Card card = new Card('D',3);
		
		if(i == 0) {
			for(int j = 0; j < 5; j++) {
				game.getPlayer().addCard(card);
			}
		} else {
			for(int j = 0; j < 5; j++) {
				game.getDealer().addCard(card);
			}
		}
	}
	public void highCards(int i) {
		Card card = new Card('S', 10);
		
		if(i == 0) {
			game.getPlayer().addCard(card);
			game.getPlayer().addCard(card);
		} else {
			game.getDealer().addCard(card);
			game.getDealer().addCard(card);
		}
	}
	public void lowCards(int i) {
		Card card1 = new Card('S', 4);
		Card card2 = new Card('S', 5);
		
		if(i == 0) {
			game.getPlayer().addCard(card1);
			game.getPlayer().addCard(card2);
		} else {
			game.getDealer().addCard(card1);
			game.getDealer().addCard(card2);
		}
	}
	public void bust(int i) {
		Card card1 = new Card('S', 10);
		Card card2 = new Card('S', 2);
		
		if(i == 0) {
			game.getPlayer().addCard(card1);
			game.getPlayer().addCard(card1);
			game.getPlayer().addCard(card2);
		} else {
			game.getDealer().addCard(card1);
			game.getDealer().addCard(card1);
			game.getDealer().addCard(card2);
		}
	}
	
	//Sjekke at riktig person vinner
	//Player BJ
		@Test
		public void testPlayerBlackJackDealerBlackJack() {
			game = new Game();
			blackJack(0);
			blackJack(1);
			checkPlayerWin();
		}
		@Test
		public void testPlayerBlackJackDealerCharlie() {
			game = new Game();
			blackJack(0);
			charlie(1);
			checkPlayerWin();
		}
		@Test
		public void testPlayerBlackJackDealerHigh() {
			game = new Game();
			blackJack(0);
			highCards(1);
			checkPlayerWin();
		}
		@Test
		public void testPlayerBlackJackDealerLow() {
			game = new Game();
			blackJack(0);
			lowCards(1);
			checkPlayerWin();
		}
		@Test
		public void testPlayerBlackJackDealerZero() {
			game = new Game();
			blackJack(0);
			checkPlayerWin();
		}
		@Test
		public void testPlayerBlackJackDealerBust() {
			game = new Game();
			blackJack(0);
			bust(1);
			checkPlayerWin();
		}
	
	//Player Charlie
		@Test
		public void testPlayerCharlieDealerBlackJack() {
			game = new Game();
			charlie(0);
			blackJack(1);
			checkPlayerWin();
		}
		@Test
		public void testPlayerCharlieDealerCharlie() {
			game = new Game();
			charlie(0);
			charlie(1);
			checkPlayerWin();
		}
		@Test
		public void testPlayerCharlieDealerHigh() {
			game = new Game();
			charlie(0);
			highCards(1);
			checkPlayerWin();
		}
		@Test
		public void testPlayerCharlieDealerLow() {
			game = new Game();
			charlie(0);
			lowCards(1);
			checkPlayerWin();
		}
		@Test
		public void testPlayerCharlieDealerZero() {
			game = new Game();
			charlie(0);
			checkPlayerWin();
		}
		@Test
		public void testPlayerCharlieDealerBust() {
			game = new Game();
			charlie(0);
			bust(1);
			checkPlayerWin();
		}
	
	//Player High
		@Test
		public void testPlayerHighDealerBlackJack() {
			game = new Game();
			highCards(0);
			blackJack(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerHighDealerCharlie() {
			game = new Game();
			highCards(0);
			charlie(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerHighDealerHigh() {
			game = new Game();
			highCards(0);
			highCards(1);
			checkEvenScore();
		}
		@Test
		public void testPlayerHighDealerLow() {
			game = new Game();
			highCards(0);
			lowCards(1);
			checkPlayerWin();
		}
		@Test
		public void testPlayerHighDealerZero() {
			game = new Game();
			highCards(0);
			checkPlayerWin();
		}
		@Test
		public void testPlayerHighDealerBust() {
			game = new Game();
			highCards(0);
			bust(1);
			checkPlayerWin();
		}
		
		
	//Player Low
		@Test
		public void testPlayerLowDealerBlackJack() {
			game = new Game();
			lowCards(0);
			blackJack(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerLowDealerCharlie() {
			game = new Game();
			lowCards(0);
			charlie(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerLowDealerHigh() {
			game = new Game();
			lowCards(0);
			highCards(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerLowDealerLow() {
			game = new Game();
			lowCards(0);
			lowCards(1);
			checkEvenScore();
		}
		@Test
		public void testPlayerLowDealerZero() {
			game = new Game();
			lowCards(0);
			checkPlayerWin();
		}
		@Test
		public void testPlayerLowDealerBust() {
			game = new Game();
			lowCards(0);
			bust(1);
			checkPlayerWin();
		}
		
		
	//Player Zero
		@Test
		public void testPlayerZeroDealerBlackJack() {
			game = new Game();
			blackJack(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerZeroDealerCharlie() {
			game = new Game();
			charlie(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerZeroDealerHigh() {
			game = new Game();
			highCards(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerZeroDealerLow() {
			game = new Game();
			lowCards(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerZeroDealerZero() {
			game = new Game();
			checkEvenScore();
		}
		@Test
		public void testPlayerZeroDealerBust() {
			game = new Game();
			bust(1);
			checkPlayerWin();
		}
	
	
	//Player Bust
		@Test
		public void testPlayerBustDealerBJ() {
			game = new Game();
			bust(0);
			blackJack(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerBustDealerCharlie() {
			game = new Game();
			bust(0);
			charlie(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerBustDealerHigh() {
			game = new Game();
			bust(0);
			highCards(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerBustDealerLow() {
			game = new Game();
			bust(0);
			lowCards(1);
			checkDealerWin();
		}
		@Test
		public void testPlayerBustDealerZero() {
			game = new Game();
			bust(0);
			checkDealerWin();
		}
		@Test
		public void testPlayerBustDealerBust() {
			game = new Game();
			bust(0);
			bust(1);
			checkDealerWin();
		}
	
	//ekstra
		@Test
		public void testAceAceEndGame() {
			game = new Game();
			Card card = new Card('D',1);
			game.getPlayer().addCard(card);
			game.getDealer().addCard(card);
			
			checkEvenScore();
		}
		@Test
		public void testDoubleAceEndGame() {
			game = new Game();
			Card card1 = new Card('S',1);
			Card card2 = new Card('H',1);
			Card card3 = new Card('D',1);
			Card card4 = new Card('C',1);
			game.getPlayer().addCard(card1);
			game.getPlayer().addCard(card2);
			game.getDealer().addCard(card3);
			game.getDealer().addCard(card4);
			
			checkEvenScore();
		}
}
