package application;

public class Dealer extends Participant {
	
	private Card hiddenCard;
	
		//Dealer M� hitte hvis verdi <= 16
		//Dealer m� holde hvis verdi >= 17
	public void dealerTurn(Deck deck) {
		//S� lenge dealer har en verdi lavere enn 17 s� skal vedkommende trekke.
		
		//legger f�rst til sitt skjulte kort
		this.cards.add(hiddenCard);
		//s� henter flere om det trengs
		while (this.getValueOfHand() < 17 && this.cards.size() < 5) {
			this.drawCard(deck);
		}
	}
	
	//setter et skjult kort som ikke ligger p� h�nden til dealeren
	public void setHiddenCard(Deck deck) {
		this.hiddenCard = deck.draw();
	}
	public Card getHiddenCard() {
		return this.hiddenCard;
	}
}
