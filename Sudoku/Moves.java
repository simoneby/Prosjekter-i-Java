package game;

public class Moves {

private int x,y;
private char num, oldnum;

public Moves(int x, int y, char num, char oldnum) {
	this.x = x;
	this.y = y;
	this.num = num;
	this.oldnum = oldnum;
}

public int getx() {
	return x;
}

public int gety() {
	return y;
}

public char getNum() {
	return num;
}

public char getoldnum() {
	return oldnum;
}

	
}
