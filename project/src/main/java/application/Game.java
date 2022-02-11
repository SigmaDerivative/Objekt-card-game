package application;

import java.io.FileNotFoundException;

import javafx.scene.layout.Pane;

public class Game extends Pane {
	
	//Alle spill er uavhengig av hverandre
	
	//Kronologisk rekkefølge for en runde/et spill
			//Alle får to kort
				//Spiller 1 med begge kortene med ansiktet opp
				//Dealer med første opp og andre ned
			//Spiller trekker til hold, bustet eller blackjack
			//Når spiller har nådd et av tilfellene er det dealer sin tur
	
	private Player player = new Player();
	private Dealer dealer = new Dealer();
	private Deck deck = new Deck();
	
	
	//Må ha metoder for å få tilgang til spiller og dealer
	public Player getPlayer() {
		return player;
	}

	public Dealer getDealer() {
		return dealer;
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	//Logikk for å starte et nytt spill
	public void start() {
		//nytt stokket dekk
		this.deck = new Deck();
		this.deck.shuffle();
		
		//resetter deltagerne.
		this.player = new Player();
		this.dealer = new Dealer();
		
		//Må gi spiller to kort
		this.player.hit(deck);
		this.player.hit(deck);		
		//Må gi dealer et kort opp og et kort opp ned
		this.dealer.drawCard(deck);
		this.dealer.setHiddenCard(deck);
	}
	
	public void hardReset() {
		ReadAndWrite.write("scores.txt", "0,0");
	}
	
	public String endGame() {
		//Henter scores fra tekstfil
		int playerScore = 0;
		int dealerScore = 0;
		try {
			String[] scores = ReadAndWrite.read("scores.txt").split(",");
			playerScore = Integer.parseInt(scores[0]);
			dealerScore = Integer.parseInt(scores[1]);
		} catch (FileNotFoundException e) {
			ReadAndWrite.write("scores.txt", "0,0");
		}
		
		//Sammenlikner verdiene og gir tilbakemelding om hvordan spillet endte
		if (this.player.getValueOfHand() == 21) { //Spilleren får 21
			playerScore++;
			ReadAndWrite.write("scores.txt", playerScore +","+dealerScore);
			return "Du vant (" + playerScore + " - " + dealerScore + "). Du fikk blackjack!";
		}
		else if (this.player.getValueOfHand() > 21) { //Spilleren går over 21
			dealerScore++;
			ReadAndWrite.write("scores.txt", playerScore +","+dealerScore);
			return "Dealeren vant (" + playerScore + " - " + dealerScore + "). Du busta!";
		}
		else if (player.getCards().size() == 5) { //Spilleren får Charlie
			playerScore++;
			ReadAndWrite.write("scores.txt", playerScore +","+dealerScore);
			return "Du vant (" + playerScore + " - " + dealerScore + "). Du fikk Charlie";
		}
		else if (this.dealer.getValueOfHand() == 21) { //Dealeren får blackjack
			dealerScore++;
			ReadAndWrite.write("scores.txt", playerScore +","+dealerScore);
			return "Dealern vant (" + playerScore + " - " + dealerScore + "). Dealern fikk blackjack!";
		}
		else if (this.dealer.getValueOfHand() > 21) { //Dealeren går over 21
			playerScore++;
			ReadAndWrite.write("scores.txt", playerScore +","+dealerScore);
			return "Du vant (" + playerScore + " - " + dealerScore + "). Dealeren busta";
		}
		else if (dealer.getCards().size() == 5) { //Dealeren får charlie
			dealerScore++;
			ReadAndWrite.write("scores.txt", playerScore +","+dealerScore);
			return "Dealern vant (" + playerScore + " - " + dealerScore + "). Dealern fikk Charlie";
		}
		else if (this.player.getValueOfHand() > this.dealer.getValueOfHand()) { //Spiller vinner med høyere verdi
			playerScore++;
			ReadAndWrite.write("scores.txt", playerScore +","+dealerScore);
			return "Du vant (" + playerScore + " - " + dealerScore + "). Du fikk bedre score enn dealern";
		}
		else  if (this.player.getValueOfHand() < this.dealer.getValueOfHand()) { //Dealer vinner med høyere verdi
			dealerScore++;
			ReadAndWrite.write("scores.txt", playerScore +","+dealerScore);
			return "Dealeren vant (" + playerScore + " - " + dealerScore + "). Du fikk lavere score enn dealern";
		}
		else { //Samme verdi og det blir da uavgjort
			return "Det ble uavgjort (" + playerScore + " - " + dealerScore + ").";
		}
	}		
}
