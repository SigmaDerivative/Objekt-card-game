package application;

public class Dealer extends Participant {
	
	private Card hiddenCard;
	
		//Dealer Må hitte hvis verdi <= 16
		//Dealer må holde hvis verdi >= 17
	public void dealerTurn(Deck deck) {
		//Så lenge dealer har en verdi lavere enn 17 så skal vedkommende trekke.
		
		//legger først til sitt skjulte kort
		this.cards.add(hiddenCard);
		//så henter flere om det trengs
		while (this.getValueOfHand() < 17 && this.cards.size() < 5) {
			this.drawCard(deck);
		}
	}
	
	//setter et skjult kort som ikke ligger på hånden til dealeren
	public void setHiddenCard(Deck deck) {
		this.hiddenCard = deck.draw();
	}
	public Card getHiddenCard() {
		return this.hiddenCard;
	}
}
