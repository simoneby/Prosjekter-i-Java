package game;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PersistentGameController {


	@FXML TextArea console;

	@FXML TextField inputField;
	@FXML TextField fileName;


	//Her maa du deklarerere spillet ditt dersom det heter noe annet enn TicTacToe
	Sudoku2 game;

	public void initialize(){
		//Her maa du opprette et objekt av spillet ditt med de argumentene som behoves for det - resten av koden vil gaa ut ifra at du har kalt den game
		game = new Sudoku2(".....2..38.273.45....6..87.9.8..5367..6...1..4513..9.8.84..3....79.512.62..8......68.257.3..........71..39..61.35.2...8.....4...3.64.95..76..58..........8.653.42......59.4.8..9.6.5..6....3..3.7.145...8.4.7...742.6.9..6....3..8.1.6..7.3.98........6...513....2..66...3..89..4.2.6...3.418.2...8.7.1..59..6...38..3....241...9...") ;
		console.setText(game.toString());
	}

	public void update() {
		console.setText(game.toString());
	}


	@FXML
	public void sendInput(){
		int x = (int)inputField.getText().charAt(0)-48;
		int y = (int)inputField.getText().charAt(2)-48;
		char digit = inputField.getText().charAt(5);
		System.out.println(digit);
		System.out.println(x);
		System.out.println(y);
		//Denne metoden kan hete hva som helst, men husk aa endre det her
		game.addNum(x, y, digit);
		update();

	}

	@FXML
	public void undo() {
		System.out.println("UNDO");
		game.undo();
		update();
	}

	@FXML
	public void redo() {
		System.out.println("REDO");
		game.redo();
		update();
	}

	@FXML
	public void save() throws IOException {
		game.save(fileName.getText());
		update();
	}

	@FXML
	public void load() throws IOException {
		game.load(fileName.getText());
		update();
	}
}
