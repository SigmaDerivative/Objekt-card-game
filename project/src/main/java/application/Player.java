package application;

public class Player extends Participant{
	
	public void hit(Deck deck) {
		//Kun mulig om man ikke har overstiget 21, og om man har mindre enn 5 kort
		if (this.getValueOfHand() < 21 && this.cards.size() < 5) {
			//Trekker et kort fra deck
			this.drawCard(deck);
		}
		//Ellers skal det ikke skje noe!
	}
}
