package application;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	//arraylisten med kortene blir fylt opp i konstruktøren
	private ArrayList<Card> cards = new ArrayList<Card>();

	public Deck() {
		for (int i = 0; i < 4; i++) {
			for (int face = 1; face <= 13; face++) {
				Card card = new Card(Card.getSuits().charAt(i), face);
				this.cards.add(card);
			}
		}
	}

	//gir ut alle kortene som string
	public String toString() {
		return this.cards.toString();
	}
	
	//stokker om tilfeldig alle kortene
	public void shuffle() {
		Random r = new Random();
		for (int i = 0; i < this.cards.size(); i++) {
			int randomNumber = r.nextInt(this.cards.size());
			Card temp = this.cards.get(randomNumber);
			this.cards.set(randomNumber, this.cards.get(i));
			this.cards.set(i, temp);
		}
	}
	
	//henter kortet på plass 0 (vi definerer det som øverste)
	public Card draw() {
		if(!this.isEmpty()) {
			Card temp = this.cards.get(0);
			this.cards.remove(0);
			return temp;
		} else {
			throw new IllegalStateException("You can't draw cards from an empty deck");
		}
	}
	
	public ArrayList<Card> getCards(){
		return this.cards;
	}

	public boolean isEmpty() {
		if(this.cards.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//for testing
	public void emptyDeck() {
		cards = new ArrayList<Card>();
	}
	public void addCard(Card card) {
		cards.add(card);
	}
	public Card getCard(int i){
		try {
			return this.cards.get(i);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}
}