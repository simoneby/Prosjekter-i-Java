package game;

public class SudokuMoves {

public int x;
public int y;
public char inputNum;
	
	
	public SudokuMoves(int x, int y, char inputNum) {
		this.x = x;
		this.y = y;
		this.inputNum = inputNum;
	}

public int getPos(int x, int y) {
	return (((y-1)*9)+ (x - 1));
}

public char getinputNum(char inputNum) {
	return inputNum;
}
	
	
}
