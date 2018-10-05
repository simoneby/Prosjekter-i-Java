package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.lang.Object;
import java.lang.Number;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Integer;

public class Sudoku2 {

	
public String inputNumString;
public Stack<Moves> undo = new Stack<Moves>();
public Stack<Moves> redo = new Stack<Moves>();
public char OldNum = ' ';
List<String> spaces;


public Sudoku2(String board) {
	this.spaces = Spaces(board);
}


public List<String> Spaces(String board) {
	List<String> spaces = new ArrayList<String>();{
	for (int i = 0; i < board.length(); i++) {
		if (board.charAt(i)=='.')        {spaces.add("   ");}
		else if (board.charAt(i)=='2')   {spaces.add("(2)");}
		else if (board.charAt(i)=='3')   {spaces.add("(3)");}
		else if (board.charAt(i)=='4')   {spaces.add("(4)");}
		else if (board.charAt(i)=='5')   {spaces.add("(5)");}
		else if (board.charAt(i)=='6')   {spaces.add("(6)");}
		else if (board.charAt(i)=='7')   {spaces.add("(7)");}
		else if (board.charAt(i)=='8')   {spaces.add("(8)");}
		else if (board.charAt(i)=='9')   {spaces.add("(9)");}
		else if (board.charAt(i)=='1')   {spaces.add("(1)");
	}
	}
	}
	return spaces;
}

public String txtSpaces(List<String> spaces) {
	String board = null;
	System.out.println(spaces);
	for (int i=0; i< spaces.size(); i++) {
		if (spaces.get(i)==" ") {
			board = board + ".";
		}
		for (int j =0; j<10; i++) {
			if (spaces.get(i) == Integer.toString(j))
				board = board + spaces.get(i);
		}
			
	}
	return board;
}

public void load(String filename) {
	
	List<String> saveFile = null;
	
	try {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		saveFile = Arrays.asList(reader.readLine().replace("[","").split(", "));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	spaces = saveFile;
	
}

public void save(String filename) {
	//String file = txtSpaces(spaces);
	PrintWriter writer = null;
	try {
		writer = new PrintWriter(filename);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	writer.println(spaces);
	writer.close();
}

	
public int coordinatesToListSpace(int x, int y) {
	return (((y-1)*9)+ (x - 1));
}
	

public void addNum(int x, int y, char inputNum) {
	OldNum = spaces.get(coordinatesToListSpace(x,y)).charAt(1);
	if (isValidInput(x,y,inputNum) == false) {
		throw new IllegalArgumentException("not valid input");
	}
	if (isFree(x,y)==false) {
		throw new IllegalArgumentException("cannot fill a prefilled place");
	}
	inputNumString = format(x,y,inputNum);
	spaces.set(coordinatesToListSpace(x, y), inputNumString);
	Moves moves = new Moves(x, y, inputNum, OldNum);
	undo.push(moves);
	redo.clear();
	

}

public String format(int x, int y, char inputNum) {
	String digit = Character.toString(inputNum);
	if (isValid(x,y,inputNum)) {
		inputNumString = " " + digit + " ";
		}
	else {
		inputNumString = " " + digit + "*";
	}
	if (inputNum == ' ') {
		inputNumString = " " + digit + " ";
	}
	return inputNumString;
	
}




public void undo() {
	if (undo.isEmpty()) {
		throw new IllegalArgumentException("Cannot undo when there is no moves");
	}	
	//det poppede objektet legges til redo
	Moves move = undo.pop();
	String number = format(move.getx(),move.gety(),move.getoldnum());
	spaces.set(coordinatesToListSpace(move.getx(),move.gety()), number);
	redo.push(move);
}

public void redo() {
	if (redo.isEmpty()) {
		throw new IllegalArgumentException("Cannot redo after making a new move");
	}
	Moves move = redo.pop();
	String number = format(move.getx(),move.gety(),move.getNum());
	spaces.set(coordinatesToListSpace(move.getx(),move.gety()), number);
	//det poppede objektet legges tilbake i undo
	undo.push(move);
}




public boolean isFree(int x, int y) {
	if (spaces.get(coordinatesToListSpace(x, y)).charAt(0)=='(')  {
		return false;
	}
	else {
		return true;
	}
	
}

public boolean isValid(int x, int y, char inputNum) { 
		int b = 1; int c = 1; int B= 10; int C= 10;
		int placeInSpaces = coordinatesToListSpace(x,y);
		int div = placeInSpaces  % 9; // finner ut hvilken kolonne tallet ligger i 
		
		for (int j = (placeInSpaces - div); j < (placeInSpaces + (9-div)); j++) {
			if (inputNum == spaces.get(j).charAt(1)) {
				return false;
			}
		}
		for (int i = 0; i < 9; i++) {
			if (inputNum == spaces.get(div+9*i).charAt(1)) {
				return false;
			}
		}
		if (x % 3 == 1)      {b = x+1; c = x+2;}   // sjekker hvor
		else if (x % 3 == 2) {c = x-1; b = x+1;}   // tallet er
		else if (x % 3 == 0) {b = x-2; c = x-1;}	  // i de små		
		if (y % 3 == 1)      {B = y+1; C = y+2;}   // firkantene
		else if (y % 3 == 2) {C = y-1; B = y+1;}   // befinner seg
		else if (y % 3 == 0) {B = y-2; C = y-1;}   // :)
		if (inputNum == spaces.get(coordinatesToListSpace(b,B)).charAt(1) ||
			inputNum == spaces.get(coordinatesToListSpace(c,B)).charAt(1) ||
			inputNum == spaces.get(coordinatesToListSpace(b,C)).charAt(1) ||
			inputNum == spaces.get(coordinatesToListSpace(c,C)).charAt(1)) {
			
			return false;
		}
		return true;
}

public boolean isValidInput(int x, int y, char inputNum) {
	if (inputNum==' ') {
		return true;
	}
	else if ( x < 1 || x > 9 || y < 1 || y > 9 || (int)inputNum < 48 || (int)inputNum > 57) { //sjekker tall mellom 1 og 9
		return false;
	}
	else {
		return true;
	}
}

	
	

public String toString() {
	String top = "      1     2     3    4     5     6    7     8     9  ";
	String horizontal =  "    ___________________________________________________";
	String lines = horizontal;
	for (int i = 0; i < 9; i++) {
		String line = " "+ (i+1) +" " + "| " + spaces.get(i*9) +" ' " + spaces.get(i*9+1)  + " ' " + spaces.get(i*9+2) + "| " + spaces.get(i*9+3) +" ' " + spaces.get(i*9+4)  + " ' " + spaces.get(i*9+5) + "| " + spaces.get(i*9+6) +" ' " + spaces.get(i*9+7)  + " ' " + spaces.get(i*9+8) + "|";
		if( i==2 || i==5  || i == 8) {
			line = line + "\n" + horizontal;
		}		
		lines = lines + "\n" + line;
	}
	return top + "\n" + lines ;
}

public static void main(String[] args) { //num og letter signaliserer plassering på brettet, digit er nummeret som settes inn
	Sudoku2 sudoku = new Sudoku2(".....2..38.273.45....6..87.9.8..5367..6...1..4513..9.8.84..3....79.512.62..8......68.257.3..........71..39..61.35.2...8.....4...3.64.95..76..58..........8.653.42......59.4.8..9.6.5..6....3..3.7.145...8.4.7...742.6.9..6....3..8.1.6..7.3.98........6...513....2..66...3..89..4.2.6...3.418.2...8.7.1..59..6...38..3....241...9...");
	
	System.out.println(sudoku);
	sudoku.load("sudokuSave2");
	System.out.println(sudoku);
	
	
	
	

}

}
