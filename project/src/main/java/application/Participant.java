package application;

import java.util.ArrayList;

public abstract class Participant {
	
	protected ArrayList<Card> cards = new ArrayList<>();
	
	//trekker �verste kort fra en kortstokk
	public Card drawCard(Deck deck) {
		if(deck.isEmpty()) {
			throw new IllegalStateException("You can't draw cards from an empty deck");
		} else {
			Card card = deck.draw();
			this.cards.add(card);
			return card;
		}
	}
	
	//regner ut verdien av kortene p� h�nden etter tallet p� kortet
	public int getValueOfHand() {
		int value = 0;
		int numberOfAces = 0;
		
		//legger til verdier og antall ess
		for(Card card: cards) {
			if(card.getFace() == 1) {
				numberOfAces += 1;
			//knekt, dronning, konge
			} else if(card.getFace() == 11 || card.getFace() == 12 || card.getFace() == 13) {
				value += 10;
			} else {
				value += card.getFace();
			}
		}
		
		//regner p� hva essene sin verdi skal v�re
		for(int i = 0; i < numberOfAces; i++) {
			//alle ess utenom siste m� ha verdi 1
			value++;
			
			//ser om siste kan ha verdi 11
			if(i == numberOfAces - 1) {
				if(value + 10 > 21) {
					
				} else {
					value += 10;
				}
			}
		}
		
		return value;
	}
	
	public ArrayList<Card> getCards() {
		return this.cards;
	}
	
	public String toString() {
		return this.cards.toString();
	}
	
	//for testing
	public void addCard(Card card) {
		this.cards.add(card);
	}
}
