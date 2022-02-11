package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller implements Initializable {
	
	@FXML Label feedback;
	
	@FXML Button hitButton, holdButton, newGameButton, hardResetButton;
	
	@FXML
	private List<ImageView> playerList;
	
	@FXML
	private List<ImageView> dealerList;
	
	//starter true for å kjøre newGame i starten
	private boolean finished = true;
	
	Game game = new Game();
	
	@FXML void newGame() {
		if(this.finished) {
			System.out.println("This is a new game");
			this.game.start();
			this.feedback.setText("Your turn");
			//fjerner tidligere bilder og setter det ene kortet opp ned
			this.startPictures();
			//skriver litt tilstander til konsollen for å gjøre det lettere å se at tilstandene
			//bak kulissene er riktige
			this.writeToConsole();
			//setter inn riktig kort der det trengs
			this.updatePictures();
			//sjekker om spilleren har fått blackjack
			if(this.game.getPlayer().getValueOfHand() == 21) {
				this.feedback.setText(this.game.endGame());
			} else {
				this.finished = false;
			}
		}
	}
	
	//fjerner scoren i systemet
	@FXML void hardReset() {
		this.game.hardReset();
		this.finished = true;
		this.newGame();
	}
	
	//spilleren trekker nytt kort om mulig
	@FXML void hit() {
		if(!this.finished) {
			System.out.println("Player hit");
			this.game.getPlayer().hit(this.game.getDeck());
			
			this.writeToConsole();
			this.updatePictures();
			
			//avslutter spillet om spilleren buster, får blackjack eller charlie
			if(game.getPlayer().getValueOfHand() > 20 || game.getPlayer().getCards().size() == 5) {
				this.feedback.setText(game.endGame());
				this.finished = true;
			}
		}
	}
	
	//spiller trekker ingen flere kort, dealer trekker sine kort og runden avsluttes
	@FXML void hold() {
		if(!this.finished) {
			System.out.println("Player holded");
			//dealer trekker alle sine kort
			this.game.getDealer().dealerTurn(this.game.getDeck());

			this.writeToConsole();
			this.updatePictures();
			//sjekker resultatet
			this.feedback.setText(this.game.endGame());
		}
		this.finished = true;
	}
	
	public void writeToConsole() {
		System.out.println("player:" + this.game.getPlayer().getCards() + " | " + this.game.getPlayer().getValueOfHand());
		System.out.println("dealer:" + this.game.getDealer().getCards() + " | " + this.game.getDealer().getValueOfHand());
	}
	
	//oppdaterer bilder
	public void updatePictures() {
		for(int i = 0; i < this.game.getPlayer().getCards().size(); i++) {
			ArrayList<Card> kort = this.game.getPlayer().getCards();
			String k = kort.get(i).toString();
			File file = new File("src/main/java/cards/" + k + ".png");
			Image image = new Image(file.toURI().toString());
			playerList.get(i).setImage(image);
		}
		for(int i = 0; i < this.game.getDealer().getCards().size(); i++) {
			ArrayList<Card> kort = this.game.getDealer().getCards();
			String k = kort.get(i).toString();
			File file = new File("src/main/java/cards/" + k + ".png");
			Image image = new Image(file.toURI().toString());
			dealerList.get(i).setImage(image);
		}
	}

	//fjerner gamle bilder og legger til dealers kort opp ned
	public void startPictures() {
		for(int i = 0; i < 5; i++) {
			if(i < 2) {
				File file = new File("src/main/java/cards/card.png");
				Image image = new Image(file.toURI().toString());
				dealerList.get(i).setImage(image);
			} else {
				File file = new File("src/main/java/cards/white.png");
				Image image = new Image(file.toURI().toString());
				dealerList.get(i).setImage(image);
			}
		}
		for(int i = 0; i < 5; i++) {
			File file = new File("src/main/java/cards/white.png");
			Image image = new Image(file.toURI().toString());
			playerList.get(i).setImage(image);
		}
	}
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		this.newGame();
    }
}
