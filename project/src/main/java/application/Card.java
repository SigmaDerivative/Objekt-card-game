package application;

public class Card{

	// farge, 'S' (spades), 'H' (hearts), 'D' (diamonds) og 'C' (clubs)
	private char suit;
	// verdien, 1 for ess, 2 - 10, 11 (knekt), 12 (dronning) og 13 (konge).
	private int face;

	// fargene i synkende rekkef�lge
	private final static String SUITS = "SHDC";

	public Card(char suit, int face) {
		//restriksjoner p� farge og tall p� kortene
		if (SUITS.indexOf(suit) == -1) {
			throw new IllegalArgumentException("Illegal suit: " + suit);
		}
		if (face < 1 || face > 13) {
			throw new IllegalArgumentException("Illegal face: " + face);
		}
		this.suit = suit;
		this.face = face;
	}
	
	//Gj�r om kortet til en string, for eks: "S5"
	public String toString() {
		return String.valueOf(this.suit) + this.face;
	}

	public char getSuit() {
		return this.suit;
	}

	public int getFace() {
		return this.face;
	}
	
	public static String getSuits() {
		return SUITS;
	}
}
